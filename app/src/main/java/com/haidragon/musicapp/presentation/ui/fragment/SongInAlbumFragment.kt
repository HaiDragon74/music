package com.haidragon.musicapp.presentation.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.haidragon.musicapp.presentation.ui.activity.PlayMusicActivity
import com.haidragon.musicapp.presentation.ui.adapter.SongStorageAdapter
import com.haidragon.musicapp.presentation.base.BaseFragment
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.databinding.FragmentSongInAlbumBinding
import com.haidragon.musicapp.service.MusicServiceResources
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongInAlbumFragment : BaseFragment<FragmentSongInAlbumBinding>() {
    companion object{
        const val KEY_SONG_TO_ALBUM = "key_song_to_album"
    }
    private lateinit var bundle: Bundle
    private var musicServiceResources: MusicServiceResources? = null
    private val songViewModel: SongViewModel by viewModels()
    private lateinit var songStorageAdapter: SongStorageAdapter
    private var nameAlbum: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MusicServiceResources) {
            musicServiceResources = context
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentSongInAlbumBinding=FragmentSongInAlbumBinding.inflate(layoutInflater, container, false)

    override fun setUpView() {
        super.setUpView()
        bundle = requireArguments()
        nameAlbum = bundle.getString(KEY_SONG_TO_ALBUM)
        songStorageAdapter = SongStorageAdapter(songViewModel)
        binding.recyclerSongInAlbum.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerSongInAlbum.adapter = songStorageAdapter
        setDataRecycler()
        onItemClickRecycler()
        onBack()
    }
    private fun onItemClickRecycler() {
        songStorageAdapter.clickItem = { song ->
            musicServiceResources?.starService(song)
            val intent = Intent(requireActivity(), PlayMusicActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setDataRecycler() {
        binding.tvNameAlbum.text = nameAlbum
        nameAlbum?.let { songViewModel.realSongByAlbum(it) }
        songViewModel.liveDataSongByAlbum.observe(requireActivity()) { listSong ->
            songStorageAdapter.setListSongStorage(listSong as MutableList<Song>)
        }
    }

    private fun onBack() {
        val navController = findNavController()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.popBackStack()
        }
    }

}