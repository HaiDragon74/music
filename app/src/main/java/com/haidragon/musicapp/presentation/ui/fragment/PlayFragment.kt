package com.haidragon.musicapp.presentation.ui.fragment

import android.animation.ValueAnimator
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.haidragon.musicapp.R
import com.haidragon.musicapp.presentation.base.BaseFragment
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.databinding.FragmentPlayBinding
import com.haidragon.musicapp.service.MusicService
import com.haidragon.musicapp.service.MusicServiceResources
import com.haidragon.musicapp.presentation.utils.Constant
import com.haidragon.musicapp.presentation.utils.OnBlackListener
import com.haidragon.musicapp.presentation.utils.UtilsAnimation
import com.haidragon.musicapp.presentation.utils.UtilsImage
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
@AndroidEntryPoint
class PlayFragment : BaseFragment<FragmentPlayBinding>() {
    companion object{
        const val KEY_SEND_SERVICE = "key_send_service"
        const val KEY_DATA_SONG = "data_song"
    }
    private var animator: ValueAnimator?=null
    private lateinit var seekBar: SeekBar
    private var position = 0
    private val songViewModel: SongViewModel by viewModels()
    private var song: Song? = null
    private var isPlay: Boolean? = null
    private var action: Int? = null
    private lateinit var activity: FragmentActivity
    private lateinit var mContext: Context
    private var broadcastReceiver: BroadcastReceiver? = null
    private var onBlackListener: OnBlackListener? = null
    private var musicServiceResources: MusicServiceResources? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
        if (context is FragmentActivity) {
            activity = context
        }
        if (context is OnBlackListener) {
            onBlackListener = context
        }
        if (context is MusicServiceResources) {
            musicServiceResources = context
        }
    }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentPlayBinding =FragmentPlayBinding.inflate(layoutInflater,container,false)

    override fun setUpView() {
        super.setUpView()
        startAnimation()
        seekBar = binding.seekBar
        getDataService()
        if (broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(mContext).registerReceiver(
                broadcastReceiver!!, IntentFilter(
                    Constant.KEY_SEND_ACTIVITY
                )
            )
        }
        reloadMusic()
        rotateImage()
        onBack()
        binding.root.setOnClickListener {
            //entity
        }
    }

    private fun startAnimation() {
        val view=binding.root
        UtilsAnimation.bottomToTop(view)
    }

    private fun reloadMusic() {
        sendToService(Constant.RELOAD, 0)
        if (MusicService.isPlay == true) {
            sendToService(Constant.START, 0)
        } else if (MusicService.isPlay == false && MusicService.isService == true) {
            sendToService(Constant.PAUSE, 0)
        } else sendToService(Constant.CLOSE, 0)
    }

    private fun getDataService() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val songJson = intent?.getStringExtra(Constant.KEY_SONG_TO_ACTIVITY)
                song = Gson().fromJson(songJson, Song::class.java)
                isPlay = intent?.getBooleanExtra(Constant.KEY_IS_PLAY_TO_ACTIVITY, false)
                action = intent?.getIntExtra(Constant.KEY_ACTION_TO_ACTIVITY, 0)
                setLayoutPlayMusic()
            }
        }
    }

    private fun setLayoutPlayMusic() {
        when (action) {
            Constant.START -> showLayout()
            Constant.PAUSE -> setStatusPause()
            Constant.RESUME -> setStatusPause()
            Constant.NEXT -> setStatusPause()
            Constant.CLOSE -> setStatusPause()
            Constant.RELOAD -> showLayout()

        }
    }

    private fun setStatusPause() {
        if (isPlay == true) {
            binding.imgPausePlay.setImageResource(R.drawable.ic_pause)
            animator?.resume()
        } else {
            animator?.pause()
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
                songViewModel.insertSongToFavourite(mContext, song!!)
            }
            binding.imgPausePlay.setOnClickListener {
                if (isPlay == true) {
                    sendToService(Constant.PAUSE, position)
                } else {
                    sendToService(Constant.RESUME, position)
                    val intentAction = Intent(activity, MusicService::class.java)
                    intentAction.putExtra(
                        Constant.KEY_SEND_SERVICE, Constant.START
                    )
                    val songJson = Gson().toJson(MusicService.song)
                    intentAction.putExtra(KEY_DATA_SONG, songJson)
                    activity?.startService(intentAction)
                }
            }
            binding.imgNextPlay.setOnClickListener {
                sendToService(Constant.NEXT, 0)
            }
            binding.imgPreviousPlay.setOnClickListener {
                sendToService(Constant.PREVIOUS, 0)
            }
            binding.imgBtAlbum.setOnClickListener {
                val sonJson = Gson().toJson(song)
                val bundle = Bundle()
                bundle.putString("key_song_to_album", sonJson)

                val dialogFragment = DiaLogSongBottomFragment()
                dialogFragment.arguments = bundle

                dialogFragment.show(childFragmentManager, "DiaLogBottomAlbumFragment")
            }
            binding.imgDown.setOnClickListener {
                onBlackListener?.OnBlack(0)
                musicServiceResources?.visibleActionPlay()
                removeCurrentFragment()
                val view = binding.root
                val slideDownAnimation = TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 1f
                )
                slideDownAnimation.duration = 500
                slideDownAnimation.fillAfter = true
                view.startAnimation(slideDownAnimation)
            }
            binding.tvTitlePlay.text = song!!.title
            binding.tvArtistPlay.text = song!!.artist
             if (song?.coverArt?.isEmpty() == true) {
                binding.imgPlaySong.setImageResource(R.drawable.ic_img_avt)
            } else {
                binding.imgPlaySong.setImageBitmap(UtilsImage.base64ToBitmap(MusicService.song.coverArt))
            }
        }
    }

    private fun seekBarMusic() {
        seekBar.max = song?.duration!!
        val timeSong = song!!.duration ?: 0
        val minutesEnd = timeSong / (1000 * 60)
        val secondsEnd = (timeSong / 1000) % 60
        val timeEnd = String.format(Locale.getDefault(),"%02d:%02d", minutesEnd, secondsEnd)
        binding.tvDurationEnd.text = timeEnd
        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekBar.progress = MusicService.mediaPlayer?.currentPosition!!
                    val timePlay = MusicService.mediaPlayer?.currentPosition ?: 0
                    val minutesStart = timePlay / (1000 * 60)
                    val secondsStart = (timePlay / 1000) % 60
                    val timeStart = String.format(Locale.getDefault(),"%02d:%02d", minutesStart, secondsStart)
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
                    sendToService(Constant.SEEKBAR, progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //entity
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //entity
            }
        })
    }
    private fun rotateImage(){
        binding.cvImgPlay.post {
            val width = binding.cvImgPlay.width
            val height = binding.cvImgPlay.height
            val size = minOf(width, height)
            binding.imgPlaySong.layoutParams = binding.imgPlaySong.layoutParams.apply {
                this.width = size
                this.height = size
            }
            binding.imgPlaySong.requestLayout()
            binding.imgPlaySong.pivotX = (size / 2).toFloat()
            binding.imgPlaySong.pivotY = (size / 2).toFloat()
            animator = ValueAnimator.ofFloat(0f, 360f)
            animator?.addUpdateListener { animation ->
                val angle = animation.animatedValue as Float
                binding.imgPlaySong.rotation = angle
            }
            animator?.duration = 20000
            animator?.repeatCount = ValueAnimator.INFINITE
            animator?.interpolator = LinearInterpolator()
            animator?.start()
        }
    }
    private fun sendToService(action: Int, position: Int?) {
        val intent = Intent(activity, MusicService::class.java)
        intent.putExtra(KEY_SEND_SERVICE, action)
        intent.putExtra("position", position)
        activity.startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        broadcastReceiver?.let { LocalBroadcastManager.getInstance(mContext).unregisterReceiver(it) }
    }
    private fun onBack() {
        activity.onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBlackListener?.OnBlack(0)
                    musicServiceResources?.visibleActionPlay()
                    removeCurrentFragment()
                    val view = binding.root
                    UtilsAnimation.topToBottom(view)
                }
            }
        )
    }
    private fun removeCurrentFragment() {
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(this)
        fragmentTransaction.commit()
    }
}