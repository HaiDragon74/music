package com.remotetechs.musicapp.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.remotetechs.musicapp.adapter.SongAdapter
import com.remotetechs.musicapp.activity.PlayMusicActivity
import com.remotetechs.musicapp.model.Song
import com.remotetechs.musicapp.databinding.FragmentHomeBinding
import com.remotetechs.musicapp.service.MusicServiceResources

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var songAdapter: SongAdapter
    private var musicServiceResources: MusicServiceResources? = null

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MusicServiceResources) {
            musicServiceResources = context
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        songAdapter = SongAdapter()
        binding.recyclerHome.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerHome.adapter = songAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkStoragePermission()
    }

    private fun checkStoragePermission() {
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    realDataSong()
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

    private fun realDataSong() {
        val song = Song()
        val list = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            song.getSongLocal(binding.root.context)
        } else {
            song.getSongLocal(binding.root.context)
        }
        songAdapter.setListSong(list)
    }

    private fun onClickItemRecycler() {
        songAdapter.clickItem = { song ->
            musicServiceResources?.starService(song)
            val intent = Intent(requireActivity(), PlayMusicActivity::class.java)
            startActivity(intent)
        }
    }

}