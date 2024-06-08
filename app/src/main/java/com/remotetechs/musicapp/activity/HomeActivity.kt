package com.remotetechs.musicapp.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.gson.Gson
import com.remotetechs.musicapp.R
import com.remotetechs.musicapp.model.Song
import com.remotetechs.musicapp.databinding.ActivityMainBinding
import com.remotetechs.musicapp.service.MusicService
import com.remotetechs.musicapp.service.MusicServiceResources


class HomeActivity : AppCompatActivity(), MusicServiceResources {
    companion object{
        const val KEY_DATA_SONG = "data_song"
        const val KEY_SEND_SERVICE = "key_send_service"
    }
    private lateinit var binding: ActivityMainBinding
    private var song: Song? = null
    private var isPlay: Boolean? = null
    private var action: Int? = null
    private var broadcastReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
        getDataService()
        reloadSong()
    }

    private fun reloadSong() {
        sendToService(MusicService.RELOAD)
        if (MusicService.isPlay == true) {
            sendToService(MusicService.START)
        } else if (MusicService.isPlay == false && MusicService.isService == true) {
            sendToService(MusicService.PAUSE)
        } else
            sendToService(MusicService.CLOSE)
    }

    private fun getDataService() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val songJson = intent?.getStringExtra(MusicService.KEY_SONG_TO_ACTIVITY)
                song = Gson().fromJson(songJson, Song::class.java)
                isPlay = intent?.getBooleanExtra(MusicService.KEY_IS_PLAY_TO_ACTIVITY, false)
                action = intent?.getIntExtra(MusicService.KEY_ACTION_TO_ACTIVITY, 0)
                setLayoutMusic()
            }
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(
            broadcastReceiver!!, IntentFilter(
                MusicService.KEY_SEND_ACTIVITY
            )
        )
    }

    private fun setLayoutMusic() {
        when (action) {
            MusicService.START -> showLayout()
            MusicService.PAUSE -> setStatusPause()
            MusicService.RESUME -> setStatusPause()
            MusicService.NEXT -> setStatusPause()
            MusicService.CLOSE -> binding.relativeLayoutActionHome.visibility = View.GONE
            MusicService.RELOAD -> showLayout()
        }
    }

    private fun setStatusPause() {
        if (isPlay == true) {
            binding.imgPauseHone.setImageResource(R.drawable.ic_pause)
        } else {
            binding.imgPauseHone.setImageResource(R.drawable.ic_resume)
        }
    }

    private fun showLayout() {
        if (song?.filePath == null) {
            binding.relativeLayoutActionHome.visibility = View.GONE
            return
        } else {
            binding.relativeLayoutActionHome.visibility = View.VISIBLE
            binding.relativeLayoutActionHome.setOnClickListener {
                val intent = Intent(this, PlayMusicActivity::class.java)
                startActivity(intent)
            }
            binding.imgPauseHone.setImageResource(R.drawable.ic_pause)
            binding.imgPauseHone.setOnClickListener {
                if (isPlay == true) {
                    sendToService(MusicService.PAUSE)
                } else {
                    sendToService(MusicService.RESUME)
                }
            }
            binding.imgNextHome.setOnClickListener {
                sendToService(MusicService.NEXT)
            }
            binding.imgPreviousHome.setOnClickListener {
                sendToService(MusicService.PREVIOUS)
            }
            binding.tvTitleHome.text = song!!.title
            binding.tvTitleHome.setSelected(true)
            binding.tvArtistHome.text = song!!.artist
        }
    }

    private fun sendToService(action: Int) {
        val intent = Intent(this, MusicService::class.java)
        intent.putExtra(KEY_SEND_SERVICE, action)
        startService(intent)
    }

    override fun starService(song: Song) {
        val intent = Intent(this, MusicService::class.java)
        val songJson = Gson().toJson(song)
        intent.putExtra(KEY_DATA_SONG, songJson)
        startService(intent)
    }

    override fun stopService() {
        TODO("Not yet implemented")
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver!!)

    }

    override fun gonMenu() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    override fun visibleMenu() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
}