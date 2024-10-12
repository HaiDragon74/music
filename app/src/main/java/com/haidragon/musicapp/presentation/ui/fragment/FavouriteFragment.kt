package com.haidragon.musicapp.presentation.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.haidragon.musicapp.presentation.ui.activity.PlayMusicActivity
import com.haidragon.musicapp.presentation.ui.adapter.SongStorageAdapter
import com.haidragon.musicapp.presentation.base.BaseFragment
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.databinding.FragmentFavouriteBinding
import com.haidragon.musicapp.service.MusicServiceResources
import com.haidragon.musicapp.presentation.utils.OnBlackListener
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {
    private lateinit var songStorageAdapter: SongStorageAdapter
    private val songViewModel: SongViewModel by viewModels()
    private var listSongFavourite: MutableList<Song> = mutableListOf()
    private var onBlackListener: OnBlackListener? = null
    private var musicServiceResources: MusicServiceResources? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MusicServiceResources) {
            musicServiceResources = context
        }
        if (context is OnBlackListener) {
            onBlackListener = context
        } else {
            throw RuntimeException("$context must implement OnBlackListener")
        }
    }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentFavouriteBinding=FragmentFavouriteBinding.inflate(layoutInflater, container, false)

    override fun setUpView() {
        super.setUpView()
        // Inflate the layout for this fragment
        songStorageAdapter = SongStorageAdapter(songViewModel)
        binding.recyclerFavorites.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerFavorites.adapter = songStorageAdapter
        songViewModel.realDataSong()
        setDataRecycler()
        onClickItemRecycler()
        onBack()
    }
    private fun setDataRecycler() {
        songViewModel.liveDataSong.observe(requireActivity()) { listSong ->
            listSongFavourite.clear()
            listSong.forEach { song ->
                if (song.nameAlbum == "Favourite") {
                    listSongFavourite.add(song)
                }
            }
            songStorageAdapter.setListSongStorage(listSongFavourite)
        }
    }

    private fun onClickItemRecycler() {
        songStorageAdapter.clickItem = { song ->
            musicServiceResources?.starService(song)
            val intent = Intent(requireActivity(), PlayMusicActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onBack() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBlackListener?.OnBlack(0)
            }
        })

    }
}