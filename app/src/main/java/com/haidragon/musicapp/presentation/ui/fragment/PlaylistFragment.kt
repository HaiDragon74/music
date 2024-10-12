package com.remotetechs.musicapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.haidragon.musicapp.databinding.FragmentPlayListBinding
import com.haidragon.musicapp.presentation.ui.fragment.RecentSongFragment
import com.haidragon.musicapp.presentation.base.BaseFragment
import com.haidragon.musicapp.presentation.ui.adapter.PlaylistAdapter
import com.haidragon.musicapp.presentation.utils.FragmentManager
import com.haidragon.musicapp.presentation.utils.SharedPref
import com.haidragon.musicapp.presentation.utils.SingletonApp
import com.haidragon.musicapp.presentation.utils.UtilsPlayList
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : BaseFragment<FragmentPlayListBinding>() {
    private lateinit var sharedPref: SharedPref
    private lateinit var playlistAdapter: PlaylistAdapter
    private val songViewModel: SongViewModel by viewModels()
    private lateinit var mContext: Context
    private lateinit var recyclerPlayList: RecyclerView
    private lateinit var activity: FragmentActivity
    private lateinit var utilsPlayList: UtilsPlayList
    @Inject lateinit var singletonApp: SingletonApp

    override fun onAttach(context: Context) {
        super.onAttach(context)
            mContext=context
        if (context is FragmentActivity){
            activity=context
        }
    }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentPlayListBinding = FragmentPlayListBinding.inflate(layoutInflater, container, false)

    override fun initUI() {
        super.initUI()
        recyclerPlayList=binding.recyclerPlayList
    }

    override fun setupListen() {
        super.setupListen()
        playlistAdapter.clickItem={playList->
            FragmentManager.switchFragment(RecentSongFragment.newInstance(playList.name),"RecentSongFragment",singletonApp,activity)
        }

    }

    override fun setUpView() {
        super.setUpView()
        utilsPlayList= UtilsPlayList(mContext)
        insertListDefaultPlaylist()
        playlistAdapter= PlaylistAdapter()
        recyclerPlayList.adapter=playlistAdapter
        sharedPref= SharedPref()
    }
    private fun insertListDefaultPlaylist() {
        utilsPlayList.listPlaylistDefault().forEach { playlist->
            songViewModel.insertPlayList(playlist)
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
        songViewModel.getPlaylists()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        songViewModel.liveDataListPlaylist.observe(viewLifecycleOwner){ listPlayList->
            playlistAdapter.submitList(listPlayList)
        }
    }
}