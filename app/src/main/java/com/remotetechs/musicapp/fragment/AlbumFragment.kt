package com.remotetechs.musicapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.remotetechs.musicapp.R
import com.remotetechs.musicapp.adapter.AlbumAdapter
import com.remotetechs.musicapp.databinding.FragmentAlbumBinding
import com.remotetechs.musicapp.model.Album
import com.remotetechs.musicapp.room.MusicDatabase
import com.remotetechs.musicapp.service.MusicServiceResources
import com.remotetechs.musicapp.viewmodel.AlbumViewModel
import com.remotetechs.musicapp.viewmodel.AlbumViewModelFactory

class AlbumFragment : Fragment() {
    companion object{
        const val KEY_SONG_TO_ALBUM = "key_song_to_album"
    }
    private lateinit var binding: FragmentAlbumBinding
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var albumAdapter: AlbumAdapter
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
        binding = FragmentAlbumBinding.inflate(layoutInflater, container, false)
        albumAdapter = AlbumAdapter()
        val albumViewModelFactory =
            AlbumViewModelFactory(MusicDatabase.getCalculateDatabase(requireActivity()))
        albumViewModel =
            ViewModelProvider(requireActivity(), albumViewModelFactory)[AlbumViewModel::class.java]
        binding.recyclerAlbum.layoutManager =
            GridLayoutManager(requireActivity(), 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerAlbum.adapter = albumAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAlbum()
        setDataRecycler()
        albumViewModel.realAlbum()
        onClickItemRecycler()
        deleteItemAlbum()
        onBack()

    }

    private fun deleteItemAlbum() {
        val itemTouHelpers = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var getAlbum: Album? = null
                val listAlbum = albumAdapter.getListAlbum()
                listAlbum.forEach { album ->
                    getAlbum = album
                }
                val position = viewHolder.adapterPosition
                if (position >= 0 && position < listAlbum.size) {
                    albumViewModel.deleteAlbumById(listAlbum[position])
                    albumAdapter.notifyItemRangeChanged(0, listAlbum.size)
                    Snackbar.make(
                        requireView(),
                        "Delete ${getAlbum!!.nameAlbum} success",
                        Snackbar.LENGTH_LONG
                    ).setAction("Undoes") {
                        albumViewModel.insertAlbum(requireActivity(), listAlbum[position])
                        albumAdapter.notifyItemRangeChanged(0, listAlbum.size)
                    }.show()
                }
            }
        }
        ItemTouchHelper(itemTouHelpers).attachToRecyclerView(binding.recyclerAlbum)
    }


    override fun onResume() {
        super.onResume()
        musicServiceResources?.visibleMenu()
    }

    private fun onClickItemRecycler() {
        albumAdapter.onClick = { album ->
            musicServiceResources?.gonMenu()
            val bundle = Bundle()
            bundle.putString(KEY_SONG_TO_ALBUM, album.nameAlbum)
            findNavController().navigate(R.id.action_albumFragment_to_songInAlbumFragment, bundle)
        }
    }

    private fun setDataRecycler() {
        albumViewModel.liveDataAlbum.observe(requireActivity()) { listAlbum ->
            albumAdapter.setListAlbum(listAlbum as MutableList<Album>)
        }
    }

    private fun createAlbum() {
        binding.cvCreateAlbum.setOnClickListener {
            DialogCreateAlbumFragment().show(childFragmentManager, "DialogCreateAlbumFragment")
        }
    }

    private fun onBack() {
        val navController = findNavController()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.popBackStack()
        }
    }
}