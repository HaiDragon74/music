package com.haidragon.musicapp.presentation.ui.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.haidragon.musicapp.R
import com.haidragon.musicapp.databinding.ActivityMainBinding
import com.haidragon.musicapp.presentation.base.BaseActivity
import com.haidragon.musicapp.presentation.ui.fragment.AlbumFragment
import com.haidragon.musicapp.presentation.ui.fragment.FavouriteFragment
import com.haidragon.musicapp.presentation.ui.fragment.HomeFragment
import com.haidragon.musicapp.presentation.ui.fragment.PlayFragment
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.service.MusicService
import com.haidragon.musicapp.service.MusicServiceResources
import com.haidragon.musicapp.presentation.utils.Constant
import com.haidragon.musicapp.presentation.utils.FragmentManager
import com.haidragon.musicapp.presentation.utils.OnBlackListener
import com.haidragon.musicapp.presentation.utils.SingletonApp
import com.haidragon.musicapp.presentation.utils.UpdateListSong
import com.haidragon.musicapp.presentation.utils.UtilsAnimation
import com.haidragon.musicapp.presentation.utils.UtilsSong.observeOnce
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>(), MusicServiceResources, OnBlackListener {
    companion object{
        const val KEY_DATA_SONG = "data_song"
        const val KEY_SEND_SERVICE = "key_send_service"
        var colorBackground:Int=0

    }
    override fun getActivityViewBinding(inflater: LayoutInflater): ActivityMainBinding  =ActivityMainBinding.inflate(layoutInflater)
    private var song: Song? = null
    var updateListSong:UpdateListSong?=null
    private var isPlay: Boolean? = null
    private var action: Int? = null
    private var broadcastReceiver: BroadcastReceiver? = null
    private var doubleFinish:Int=0
    private lateinit var tvExit:TextView
    @Inject lateinit var singletonApp: SingletonApp
    private val songViewModel :SongViewModel by viewModels()
    override fun setupView() {
        super.setupView()
        SingletonApp().initContext(this)
        val background = binding.root.background
        if (background is ColorDrawable) {
            colorBackground = background.color
        }
        FragmentManager.switchFragment(HomeFragment(), "HomeFragment",singletonApp,this)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    FragmentManager.switchFragment(HomeFragment(), "HomeFragment",singletonApp,this)
                    true
                }
                R.id.favouriteFragment -> {
                    FragmentManager.switchFragment(FavouriteFragment(), "FavouriteFragment",singletonApp,this)
                    true
                }
                R.id.albumFragment -> {
                    FragmentManager.switchFragment(AlbumFragment(), "AlbumFragment",singletonApp,this)
                    true
                }
                else -> false
            }
        }
        getDataService()
        reloadSong()
        if (singletonApp.activeFragment?.tag=="HomeFragment"){
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    doubleFinish++
                    if (doubleFinish==2){
                        finish()
                    }else if (doubleFinish==1){
                        tvExit.visibility=View.VISIBLE
                    }
                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleFinish=0
                        tvExit.visibility=View.GONE
                    },2000)
                }
            })
        }else{
            //no fragment home
        }
        hideSystemUI()
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
    override fun initUI() {
        super.initUI()
        tvExit=binding.tvExit
    }
    override fun OnBlack(position: Int) {
        if (singletonApp.activeFragment?.tag=="HomeFragment"){
            doubleFinish++
            if (doubleFinish==2){
                finish()
            }else if (doubleFinish==1){
                tvExit.visibility=View.VISIBLE
            }
            Handler(Looper.getMainLooper()).postDelayed({
                doubleFinish=0
                tvExit.visibility=View.GONE
            },2000)
        }else{
            FragmentManager.switchFragment(HomeFragment(), "HomeFragment",singletonApp,this)
            val menu = binding.bottomNavigationView.menu
            val menuItemHome = menu.getItem(position)
            menuItemHome.isChecked = true
        }

    }
    private fun reloadSong() {
        sendToService(Constant.RELOAD)
        if (MusicService.isPlay == true) {
            sendToService(Constant.START)
        } else if (MusicService.isPlay == false && MusicService.isService == true) {
            sendToService(Constant.PAUSE)
        } else
            sendToService(Constant.CLOSE)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        songViewModel.liveDatSongQueue.observeOnce(this) { songQueue ->
            if (songQueue.listSongQueue.isNotEmpty()){
                binding.relativeLayoutActionHome.visibility=View.VISIBLE
                binding.tvTitleHome.text = songQueue.songPlay.title
                binding.tvArtistHome.text = songQueue.songPlay.artist
            }else{
                binding.relativeLayoutActionHome.visibility=View.GONE
            }
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
        songViewModel.realSongQueue()
    }

    private fun getDataService() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val songJson = intent?.getStringExtra(Constant.KEY_SONG_TO_ACTIVITY)
                song = Gson().fromJson(songJson, Song::class.java)
                isPlay = intent?.getBooleanExtra(Constant.KEY_IS_PLAY_TO_ACTIVITY, false)
                action = intent?.getIntExtra(Constant.KEY_ACTION_TO_ACTIVITY, 0)
                setLayoutMusic()
            }
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(
            broadcastReceiver!!, IntentFilter(
                Constant.KEY_SEND_ACTIVITY
            )
        )
    }

    private fun setLayoutMusic() {
        when (action) {
            Constant.START -> showLayout()
            Constant.PAUSE -> setStatusPause()
            Constant.RESUME -> setStatusPause()
            Constant.NEXT -> setStatusPause()
            Constant.CLOSE -> binding.relativeLayoutActionHome.visibility = View.GONE
            Constant.RELOAD -> showLayout()
        }
    }

    private fun setStatusPause() {
        if (isPlay == true) {
            singletonApp.isPlaying=true
            binding.imgPauseHone.setImageResource(R.drawable.ic_pause)
        } else {
            singletonApp.isPlaying=false
            binding.imgPauseHone.setImageResource(R.drawable.ic_resume)
        }
    }

    private fun showLayout() {
        if (song?.filePath == null) {
            binding.relativeLayoutActionHome.visibility = View.GONE
            return
        } else if (isPlay==true) {
            binding.relativeLayoutActionHome.setOnClickListener {
                FragmentManager.switchFragment(PlayFragment(),"PlayFragment",singletonApp,this)
                UtilsAnimation.showHomeFragment(this)
            }
            binding.imgPauseHone.setImageResource(R.drawable.ic_pause)
            binding.imgPauseHone.setOnClickListener {
                if (isPlay == true) {
                    sendToService(Constant.PAUSE)
                } else {
                    sendToService(Constant.RESUME)
                }
            }
            binding.imgNextHome.setOnClickListener {
                sendToService(Constant.NEXT)
            }
            binding.imgPreviousHome.setOnClickListener {
                sendToService(Constant.PREVIOUS)
            }
            song?.let { updateListSong?.notifyDataChanged(it) }
            binding.tvTitleHome.text = song!!.title
            binding.tvTitleHome.setSelected(true)
            binding.tvArtistHome.text = song!!.artist
        }else{
            binding.imgPauseHone.setImageResource(R.drawable.ic_resume)
            binding.imgPauseHone.setOnClickListener {
                singletonApp.songPlay?.let { it1 -> starService(it1) }
            }
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
        //stopService
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

    override fun goneActionPlay() {
        binding.relativeLayoutActionHome.visibility=View.GONE
    }

    override fun visibleActionPlay() {
        binding.relativeLayoutActionHome.visibility=View.VISIBLE
    }
}