package com.remotetechs.musicapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.remotetechs.musicapp.adapter.AlbumBottomAdapter
import com.remotetechs.musicapp.databinding.FragmentDiaLogBottomAlbumBinding
import com.remotetechs.musicapp.model.Album
import com.remotetechs.musicapp.model.Song
import com.remotetechs.musicapp.room.MusicDatabase
import com.remotetechs.musicapp.viewmodel.AlbumViewModel
import com.remotetechs.musicapp.viewmodel.AlbumViewModelFactory
import com.remotetechs.musicapp.viewmodel.SongViewModel
import com.remotetechs.musicapp.viewmodel.SongViewModelFactory

class DiaLogAlbumBottomFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDiaLogBottomAlbumBinding
    private lateinit var albumBottomAdapter: AlbumBottomAdapter
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var songViewModel: SongViewModel
    private lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDiaLogBottomAlbumBinding.inflate(layoutInflater, container, false)
        bundle = requireArguments()
        albumBottomAdapter = AlbumBottomAdapter()
        val albumViewModelFactory =
            AlbumViewModelFactory(MusicDatabase.getCalculateDatabase(requireActivity()))
        albumViewModel =
            ViewModelProvider(requireActivity(), albumViewModelFactory)[AlbumViewModel::class.java]
        val musicViewModelFactory =
            SongViewModelFactory(MusicDatabase.getCalculateDatabase(requireActivity()))
        songViewModel = ViewModelProvider(this, musicViewModelFactory)[SongViewModel::class.java]
        binding.recyclerBottomAlbum.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerBottomAlbum.adapter = albumBottomAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataRecycler()
        onClickItemRecycler()
    }

    private fun onClickItemRecycler() {
        val songJson = bundle.getString("key_song_to_album")
        val song = Gson().fromJson(songJson, Song::class.java)
        albumBottomAdapter.onClickItem = { album ->
            song.nameAlbum = album.nameAlbum
            songViewModel.insertSongToAlbum(requireActivity(), song)
        }

    }

    private fun setDataRecycler() {
        albumViewModel.realAlbum()
        albumViewModel.liveDataAlbum.observe(requireActivity()) { listAlbum ->
            albumBottomAdapter.setListBottomAlbum(listAlbum as MutableList<Album>)
        }
    }
}