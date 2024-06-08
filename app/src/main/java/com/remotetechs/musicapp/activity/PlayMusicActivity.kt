package com.remotetechs.musicapp.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.remotetechs.musicapp.R
import com.remotetechs.musicapp.model.Song
import com.remotetechs.musicapp.databinding.ActivityPlayMusicBinding
import com.remotetechs.musicapp.fragment.DiaLogAlbumBottomFragment
import com.remotetechs.musicapp.room.MusicDatabase
import com.remotetechs.musicapp.service.MusicBroadcastReceiver
import com.remotetechs.musicapp.service.MusicService
import com.remotetechs.musicapp.viewmodel.SongViewModel
import com.remotetechs.musicapp.viewmodel.SongViewModelFactory
import java.lang.Exception

class PlayMusicActivity : AppCompatActivity() {
    companion object{
        const val KEY_SEND_SERVICE = "key_send_service"
        const val KEY_DATA_SONG = "data_song"
    }
    private lateinit var binding: ActivityPlayMusicBinding
    private lateinit var seekBar: SeekBar
    private var position = 0
    private lateinit var songViewModel: SongViewModel
    private var song: Song? = null
    private var isPlay: Boolean? = null
    private var action: Int? = null
    private var broadcastReceiver: BroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayMusicBinding.inflate(layoutInflater)
        seekBar = binding.seekBar
        setContentView(binding.root)
        val songViewModelFactory = SongViewModelFactory(MusicDatabase.getCalculateDatabase(this))
        songViewModel = ViewModelProvider(this, songViewModelFactory)[SongViewModel::class.java]
        getDataService()
        if (broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this).registerReceiver(
                broadcastReceiver!!, IntentFilter(
                    MusicService.KEY_SEND_ACTIVITY
                )
            )
        }
        reloadMusic()
    }

    private fun reloadMusic() {
        sendToService(MusicService.RELOAD, 0)
        if (MusicService.isPlay == true) {
            sendToService(MusicService.START, 0)
        } else if (MusicService.isPlay == false && MusicService.isService == true) {
            sendToService(MusicService.PAUSE, 0)
        } else sendToService(MusicService.CLOSE, 0)
    }

    private fun getDataService() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val songJson = intent?.getStringExtra(MusicService.KEY_SONG_TO_ACTIVITY)
                song = Gson().fromJson(songJson, Song::class.java)
                isPlay = intent?.getBooleanExtra(MusicService.KEY_IS_PLAY_TO_ACTIVITY, false)
                action = intent?.getIntExtra(MusicService.KEY_ACTION_TO_ACTIVITY, 0)
                setLayoutPlayMusic()
            }
        }
    }

    private fun setLayoutPlayMusic() {
        when (action) {
            MusicService.START -> showLayout()
            MusicService.PAUSE -> setStatusPause()
            MusicService.RESUME -> setStatusPause()
            MusicService.NEXT -> setStatusPause()
            MusicService.CLOSE -> setStatusPause()
            MusicService.RELOAD -> showLayout()

        }
    }

    private fun setStatusPause() {
        if (isPlay == true) {
            binding.imgPausePlay.setImageResource(R.drawable.ic_pause)
        } else {
            binding.imgPausePlay.setImageResource(R.drawable.ic_resume)
        }
    }

    private fun showLayout() {
        if (song?.filePath == null) {
            return
        } else {
            seekBarMusic()
            binding.imgFavoritePlay.setOnClickListener {
                song!!.nameAlbum = "Favourite"
                songViewModel.insertSongToFavourite(this, song!!)
            }
            binding.imgPausePlay.setOnClickListener {
                if (isPlay == true) {
                    sendToService(MusicService.PAUSE, position)
                } else {
                    sendToService(MusicService.RESUME, position)
                    val intentAction = Intent(this, MusicService::class.java)
                    intentAction.putExtra(
                        MusicBroadcastReceiver.KEY_SEND_SERVICE, MusicService.START
                    )
                    val songJson = Gson().toJson(MusicService.song)
                    intentAction.putExtra(KEY_DATA_SONG, songJson)
                    startService(intentAction)
                }
            }
            binding.imgNextPlay.setOnClickListener {
                sendToService(MusicService.NEXT, 0)
            }
            binding.imgPreviousPlay.setOnClickListener {
                sendToService(MusicService.PREVIOUS, 0)
            }
            binding.imgBtAlbum.setOnClickListener {
                val sonJson = Gson().toJson(song)
                val bundle = Bundle()
                bundle.putString("key_song_to_album", sonJson)

                val dialogFragment = DiaLogAlbumBottomFragment()
                dialogFragment.arguments = bundle

                dialogFragment.show(supportFragmentManager, "DiaLogBottomAlbumFragment")
            }
            binding.tvTitlePlay.text = song!!.title
            binding.tvArtistPlay.text = song!!.artist
            if (song!!.coverArt == null) {
                binding.imgPlaySong.setImageResource(R.drawable.ic_img_avt)
            } else binding.imgPlaySong.setImageBitmap(song!!.coverArt)

        }
    }

    private fun seekBarMusic() {
        seekBar.max = song?.duration!!
        val timeSong = song!!.duration ?: 0
        val minutesEnd = timeSong / (1000 * 60)
        val secondsEnd = (timeSong / 1000) % 60
        val timeEnd = String.format("%02d:%02d", minutesEnd, secondsEnd)
        binding.tvDurationEnd.text = timeEnd
        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekBar.progress = MusicService.mediaPlayer?.currentPosition!!
                    val timePlay = MusicService.mediaPlayer?.currentPosition ?: 0
                    val minutesStart = timePlay / (1000 * 60)
                    val secondsStart = (timePlay / 1000) % 60
                    val timeStart = String.format("%02d:%02d", minutesStart, secondsStart)
                    binding.tvDurationStart.text = timeStart
                    handler.postDelayed(this, 1000)
                } catch (err: Exception) {
                    seekBar.progress = 0
                }
            }
        }, 0)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    position = progress
                    sendToService(MusicService.SEEKBAR, progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun onStop() {
        super.onStop()
    }

    private fun sendToService(action: Int, position: Int?) {
        val intent = Intent(this, MusicService::class.java)
        intent.putExtra(KEY_SEND_SERVICE, action)
        intent.putExtra("position", position)
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        broadcastReceiver?.let { LocalBroadcastManager.getInstance(this).unregisterReceiver(it) }
    }

}