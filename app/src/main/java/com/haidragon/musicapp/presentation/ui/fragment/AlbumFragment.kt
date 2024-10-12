package com.haidragon.musicapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.remotetechs.musicapp.presentation.ui.adapter.AlbumAdapter
import com.haidragon.musicapp.presentation.base.BaseFragment
import com.haidragon.musicapp.domain.model.Album
import com.haidragon.musicapp.databinding.FragmentAlbumBinding
import com.haidragon.musicapp.service.MusicServiceResources
import com.haidragon.musicapp.presentation.utils.OnBlackListener
import com.haidragon.musicapp.presentation.viewmodel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : BaseFragment<FragmentAlbumBinding>() {
    companion object{
        const val KEY_SONG_TO_ALBUM = "key_song_to_album"
    }
    private lateinit var albumAdapter: AlbumAdapter
    private val albumViewModel: AlbumViewModel by viewModels()
    private var musicServiceResources: MusicServiceResources? = null
    private var onBlackListener: OnBlackListener?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MusicServiceResources) {
            musicServiceResources = context
        }else{
            throw RuntimeException("$context must implement MusicServiceResources")
        }
        if (context is OnBlackListener){
            onBlackListener = context
        }else{
            throw RuntimeException("$context must implement OnBlackListener")
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentAlbumBinding =FragmentAlbumBinding.inflate(layoutInflater, container, false)

    override fun setUpView() {
        super.setUpView()
        // Inflate the layout for this fragment
        albumAdapter = AlbumAdapter()
        binding.recyclerAlbum.layoutManager =
            GridLayoutManager(requireActivity(), 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerAlbum.adapter = albumAdapter
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
//            findNavController().navigate(R.id.action_albumFragment_to_songInAlbumFragment, bundle)
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
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBlackListener?.OnBlack(0)
            }
        })
    }
}