package com.haidragon.musicapp.presentation.ui.fragment

import android.Manifest
import android.animation.ValueAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.haidragon.musicapp.presentation.ui.adapter.SongAdapter
import com.haidragon.musicapp.presentation.base.BaseFragment
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.databinding.FragmentSongBinding
import com.haidragon.musicapp.presentation.ui.activity.HomeActivity
import com.haidragon.musicapp.service.MusicServiceResources
import com.haidragon.musicapp.presentation.utils.Constant
import com.haidragon.musicapp.presentation.utils.FragmentManager
import com.haidragon.musicapp.presentation.utils.OnBlackListener
import com.haidragon.musicapp.presentation.utils.UtilsSong
import com.haidragon.musicapp.presentation.utils.PlaylistDirector
import com.haidragon.musicapp.presentation.utils.SharedPref
import com.haidragon.musicapp.presentation.utils.SingletonApp
import com.haidragon.musicapp.presentation.utils.SongQueueDirector
import com.haidragon.musicapp.presentation.utils.UpdateListSong
import com.haidragon.musicapp.presentation.utils.UtilsAnimation
import com.haidragon.musicapp.presentation.utils.UtilsSong.observeOnce
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylistRecentlyPlayed
import com.haidragon.musicapp.presentation.utils.build.BuildSongQueue
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SongFragment : BaseFragment<FragmentSongBinding>(),UpdateListSong {
    private lateinit var mContext: Context
    private lateinit var songAdapter: SongAdapter
    private lateinit var sharedPref: SharedPref
    private lateinit var activity: FragmentActivity
    private lateinit var frameLayoutPlayMusic: FrameLayout
    private lateinit var frameLayoutShuffle: FrameLayout
    private lateinit var frameLayoutRecentSong: FrameLayout
    private lateinit var swipeRefreshLayoutSong: SwipeRefreshLayout
    private lateinit var sharedPreferences: SharedPreferences
    private val directorPlaylist = PlaylistDirector()
    private var onBlackListener: OnBlackListener? = null
    private lateinit var buildPlaylistRecentlyPlayed: BuildPlaylistRecentlyPlayed
    private var musicServiceResources: MusicServiceResources? = null
    private val songViewModel: SongViewModel by viewModels()
    private val songQueueDirector = SongQueueDirector()
    private val buildSongQueue = BuildSongQueue()

    @Inject
    lateinit var singletonApp: SingletonApp
    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPref = SharedPref()
        mContext = context
        if (context is MusicServiceResources) {
            musicServiceResources = context
        }
        if (context is FragmentActivity) {
            activity = context
        }
        if (context is OnBlackListener) {
            onBlackListener = context
        } else {
            throw ClassCastException("$context must implement OnBlackListener")
        }

    }

    override fun initUI() {
        super.initUI()
        swipeRefreshLayoutSong = binding.swipeRefreshLayoutSong
        frameLayoutPlayMusic = binding.frameLayoutPlayMusic
        frameLayoutRecentSong = binding.frameLayoutRecentSong
        frameLayoutShuffle = binding.frameLayoutShuffle

    }


    override fun setupListen() {
        super.setupListen()
        swipeRefreshLayoutSong.setOnRefreshListener {
            // Perform your data refresh operation here
            refreshData()
        }
        frameLayoutPlayMusic.setOnClickListener {
            playSongLastOrRandom()
        }
        frameLayoutShuffle.setOnClickListener {
            sharedPreferences.edit().putString(Constant.SONG_QUEUE, "Shuffle").apply()
            val song = singletonApp.listSongs.random()
            startServiceBySong(song)
        }
        frameLayoutRecentSong.setOnClickListener {
            FragmentManager.switchFragment(
                RecentSongFragment(),
                "RecentSongFragment",
                singletonApp,
                activity
            )
        }
    }

    private fun playSongLastOrRandom() {
        val songJson = sharedPreferences.getString(Constant.KEY_SAVE_SONG_PLAY_LAST, null)
        if (songJson == null) {
            val song = singletonApp.listSongs.random()
            startServiceBySong(song)
        } else {
            val song = Gson().fromJson(songJson, Song::class.java)
            startServiceBySong(song)
        }
    }

    private fun startServiceBySong(song: Song) {
        musicServiceResources?.starService(song)
        FragmentManager.switchFragment(PlayFragment(), "PlayFragment", singletonApp, activity)
        UtilsAnimation.showHomeFragment(activity)
    }

    private fun refreshData() {
        // Simulate a data refresh with a delay
        Handler(Looper.getMainLooper()).postDelayed({
            val fadeAnimator = ValueAnimator.ofFloat(0f, 1f)
            fadeAnimator.addUpdateListener { animation ->
                binding.root.alpha = animation.animatedValue as Float
            }
            fadeAnimator.duration = 500
            fadeAnimator.start()
            //load song
            swipeRefreshLayoutSong.isRefreshing = false
        }, 500)
        UtilsSong.getSongLocal(mContext).onEach { listSong ->
            songViewModel.getListSong(listSong)
            singletonApp.listSongs = listSong
        }.launchIn(lifecycleScope)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentSongBinding = FragmentSongBinding.inflate(layoutInflater, container, false)

    override fun setUpView() {
        super.setUpView()
        (activity as HomeActivity).updateListSong = this
        buildPlaylistRecentlyPlayed = BuildPlaylistRecentlyPlayed()
        directorPlaylist.construct(buildPlaylistRecentlyPlayed, listOf())
        sharedPreferences =
            mContext.getSharedPreferences(Constant.KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        songAdapter = SongAdapter()
        songAdapter.setSingletonApp(singletonApp)
        binding.recyclerHome.layoutManager =
            GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerHome.adapter = songAdapter
        checkStoragePermission()
    }

    override fun onResume() {
        super.onResume()
        val indexSongPlay = singletonApp.listSongs.indexOf(singletonApp.songPlay)
        songAdapter.notifyItemChanged(indexSongPlay)
    }

    private fun checkStoragePermission() {
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    setupViewModel()
                    onClickItemRecycler()
                    Toast.makeText(
                        requireActivity(),
                        "access granted successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else
                    Toast.makeText(
                        requireActivity(),
                        "Access permission has not been granted",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_AUDIO)
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
        if (singletonApp.listSongs.isNotEmpty()) {
            songAdapter.submitList(singletonApp.listSongs.toMutableList())
        } else {
            UtilsSong.getSongLocal(mContext).onEach { listSong ->
                songViewModel.getListSong(listSong)
            }.launchIn(lifecycleScope)
        }
        songViewModel.realPlaylistByName(buildPlaylistRecentlyPlayed.resultPlaylist().name)
        songViewModel.realSongQueue()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        songViewModel.liveDataListSong.observeOnce(viewLifecycleOwner) { listSong ->
            songAdapter.submitList(listSong.toMutableList())
            singletonApp.listSongs = listSong
        }
        songViewModel.liveDataPlaylist.observeOnce(viewLifecycleOwner) { playlist ->
            buildPlaylistRecentlyPlayed.setListFilePathSong(playlist.listSongInPlaylist)
        }
        songViewModel.liveDatSongQueue.observeOnce(viewLifecycleOwner) { songQueue ->
            songQueueDirector.construct(buildSongQueue,songQueue.listSongQueue,songQueue.songPlay)
            singletonApp.listSongsPlay=songQueue.listSongQueue.toMutableList()
            singletonApp.songPlay=songQueue.songPlay
        }

    }

    private fun onClickItemRecycler() {
        songAdapter.clickItem = { song ->
            if (buildSongQueue.getResult().listSongQueue.isEmpty()) {
                songQueueDirector.construct(buildSongQueue, listOf(song),song)
                songViewModel.insertSongQueue(buildSongQueue.getResult())
            } else {
                val listSongQueue = buildSongQueue.getResult().listSongQueue.toMutableList()
                val listSongQueueExist = buildSongQueue.getResult().listSongQueue.toMutableList()
                    .any { it.filePath == song.filePath }
                songQueueDirector.construct(buildSongQueue, listSongQueue,song)
                if (listSongQueueExist) {
                    songViewModel.updateSongQueue(buildSongQueue.getResult())
                } else {
                    listSongQueue.add(song)
                    songQueueDirector.construct(buildSongQueue, listSongQueue,song)
                    songViewModel.updateSongQueue(buildSongQueue.getResult())
                }
            }

            val namePlaylist = buildPlaylistRecentlyPlayed.resultPlaylist().name
            val listFilePathInPlaylist =
                buildPlaylistRecentlyPlayed.resultPlaylist().listSongInPlaylist.toMutableList()
            listFilePathInPlaylist.add(song)
            songViewModel.updatePlaylistFilePathByName(namePlaylist, listFilePathInPlaylist)
            singletonApp.songPlay = song
            singletonApp.isPlaying = true
            startServiceBySong(song)
            musicServiceResources?.goneActionPlay()
        }
        songAdapter.clickMenu = { _, song ->
            val diaLogSongBottomFragment = DiaLogSongBottomFragment()
            val songJson = Gson().toJson(song)
            val songPlayJson = Gson().toJson(buildSongQueue.getResult().songPlay)
            val bundle = Bundle()
            bundle.putString(Constant.SEND_SONG_DIALOG_BOTTOM, songJson)
            bundle.putString(Constant.SEND_SONG_PLAY_DIALOG_BOTTOM, songPlayJson)
            diaLogSongBottomFragment.arguments = bundle
            diaLogSongBottomFragment.show(childFragmentManager, "DiaLogSongBottomFragment")
        }
    }

    override fun notifyDataChanged(song: Song) {
        songAdapter.updateSong(song)
    }
}