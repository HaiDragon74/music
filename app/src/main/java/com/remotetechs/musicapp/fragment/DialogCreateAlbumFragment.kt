package com.remotetechs.musicapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.remotetechs.musicapp.R
import com.remotetechs.musicapp.databinding.FragmentDialogCreateAblbumBinding
import com.remotetechs.musicapp.model.Album
import com.remotetechs.musicapp.room.MusicDatabase
import com.remotetechs.musicapp.viewmodel.AlbumViewModel
import com.remotetechs.musicapp.viewmodel.AlbumViewModelFactory

class DialogCreateAlbumFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogCreateAblbumBinding
    private lateinit var albumViewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogCreateAblbumBinding.inflate(layoutInflater, container, false)
        val albumViewModelFactory =
            AlbumViewModelFactory(MusicDatabase.getCalculateDatabase(requireActivity()))
        albumViewModel =
            ViewModelProvider(requireActivity(), albumViewModelFactory)[AlbumViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOk.setOnClickListener {
            val nameAlbum = binding.tvNewAlbum.text.toString()
            if (nameAlbum.isEmpty()) {
                Toast.makeText(requireActivity(), "Album name cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            } else {
                albumViewModel.insertAlbum(requireActivity(), Album(nameAlbum = nameAlbum))
                dismiss()
            }
        }

    }
}