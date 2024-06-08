package com.remotetechs.musicapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.remotetechs.musicapp.activity.PlayMusicActivity
import com.remotetechs.musicapp.adapter.SongStorageAdapter
import com.remotetechs.musicapp.databinding.FragmentFavouriteBinding
import com.remotetechs.musicapp.model.Song
import com.remotetechs.musicapp.room.MusicDatabase
import com.remotetechs.musicapp.service.MusicServiceResources
import com.remotetechs.musicapp.viewmodel.SongViewModel
import com.remotetechs.musicapp.viewmodel.SongViewModelFactory

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var songStorageAdapter: SongStorageAdapter
    private lateinit var songViewModel: SongViewModel
    private var listSongFavourite: MutableList<Song> = mutableListOf()
    private var musicServiceResources: MusicServiceResources? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MusicServiceResources) {
            musicServiceResources = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        val songViewModelFactory =
            SongViewModelFactory(MusicDatabase.getCalculateDatabase(requireActivity()))
        songViewModel = ViewModelProvider(this, songViewModelFactory)[SongViewModel::class.java]
        songStorageAdapter = SongStorageAdapter(songViewModel)
        binding.recyclerFavorites.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerFavorites.adapter = songStorageAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        val navController = findNavController()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.popBackStack()
        }
    }
}