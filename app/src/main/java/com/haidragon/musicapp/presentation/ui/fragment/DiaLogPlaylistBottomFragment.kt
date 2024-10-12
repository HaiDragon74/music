package com.haidragon.musicapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.haidragon.musicapp.R
import com.haidragon.musicapp.databinding.FragmentDiaLogBottomPlaylistBinding
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.databinding.FragmentDiaLogBottomSongBinding
import com.haidragon.musicapp.presentation.base.BaseBottomFragment
import com.haidragon.musicapp.presentation.ui.adapter.PlaylistAdapter
import com.haidragon.musicapp.presentation.utils.Constant
import com.haidragon.musicapp.presentation.utils.SingletonApp
import com.haidragon.musicapp.presentation.utils.SongQueueDirector
import com.haidragon.musicapp.presentation.utils.UtilsSong.observeOnce
import com.haidragon.musicapp.presentation.utils.build.BuildSongQueue
import com.haidragon.musicapp.presentation.utils.build.SongBuilder
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import com.remotetechs.musicapp.presentation.ui.adapter.SongInPlayListAdapter
import dagger.hilt.android.AndroidEntryPoint
import dev.mortezaom.mdtoast.lSuccessToast
import javax.inject.Inject

@AndroidEntryPoint
class DiaLogPlaylistBottomFragment : BaseBottomFragment<FragmentDiaLogBottomPlaylistBinding>() {
    private lateinit var playListAdapter: PlaylistAdapter
    private lateinit var mcontext: Context
    private lateinit var recyclerPlaylistBottom:RecyclerView
    private val songViewModel: SongViewModel by viewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mcontext = context
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentDiaLogBottomPlaylistBinding =
        FragmentDiaLogBottomPlaylistBinding.inflate(layoutInflater, container, false)

    override fun initView() {
        super.initView()
        recyclerPlaylistBottom=binding.recyclerPlaylistBottom
    }

    override fun setupView() {
        super.setupView()
        playListAdapter = PlaylistAdapter()
        recyclerPlaylistBottom.adapter=playListAdapter

    }

    override fun setupListen() {
        super.setupListen()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        songViewModel.liveDataListPlaylistBottom.observeOnce(viewLifecycleOwner) {listPlaylist->
            playListAdapter.submitList(listPlaylist)
            playListAdapter.menu={imageButton ->
                imageButton.visibility=View.GONE
            }
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
        songViewModel.realPlaylistBottom(
            mcontext.getString(R.string.recently_added),
            mcontext.getString(R.string.recently_played),
            mcontext.getString(R.string.my_top_tracks)
        )
    }


}