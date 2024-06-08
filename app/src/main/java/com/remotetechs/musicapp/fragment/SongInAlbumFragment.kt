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
import com.remotetechs.musicapp.databinding.FragmentSongInAlbumBinding
import com.remotetechs.musicapp.model.Song
import com.remotetechs.musicapp.room.MusicDatabase
import com.remotetechs.musicapp.service.MusicServiceResources
import com.remotetechs.musicapp.viewmodel.SongViewModel
import com.remotetechs.musicapp.viewmodel.SongViewModelFactory

class SongInAlbumFragment : Fragment() {
    companion object{
        const val KEY_SONG_TO_ALBUM = "key_song_to_album"
    }
    private lateinit var binding: FragmentSongInAlbumBinding
    private lateinit var bundle: Bundle
    private var musicServiceResources: MusicServiceResources? = null
    private lateinit var songViewModel: SongViewModel
    private lateinit var songStorageAdapter: SongStorageAdapter
    private var nameAlbum: String? = null

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
        binding = FragmentSongInAlbumBinding.inflate(layoutInflater, container, false)

        bundle = requireArguments()
        nameAlbum = bundle.getString(KEY_SONG_TO_ALBUM)
        val songViewModelFactory =
            SongViewModelFactory(MusicDatabase.getCalculateDatabase(requireActivity()))
        songViewModel = ViewModelProvider(this, songViewModelFactory)[SongViewModel::class.java]
        songStorageAdapter = SongStorageAdapter(songViewModel)
        binding.recyclerSongInAlbum.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerSongInAlbum.adapter = songStorageAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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