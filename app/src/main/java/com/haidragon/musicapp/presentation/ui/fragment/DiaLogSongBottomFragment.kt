package com.haidragon.musicapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.databinding.FragmentDiaLogBottomSongBinding
import com.haidragon.musicapp.presentation.base.BaseBottomFragment
import com.haidragon.musicapp.presentation.utils.Constant
import com.haidragon.musicapp.presentation.utils.SingletonApp
import com.haidragon.musicapp.presentation.utils.SongQueueDirector
import com.haidragon.musicapp.presentation.utils.build.BuildSongQueue
import com.haidragon.musicapp.presentation.utils.build.SongBuilder
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.mortezaom.mdtoast.lSuccessToast
import javax.inject.Inject

@AndroidEntryPoint
class DiaLogSongBottomFragment : BaseBottomFragment<FragmentDiaLogBottomSongBinding>() {
    @Inject
    lateinit var singletonApp: SingletonApp
    private lateinit var tvNextPlay: TextView
    private lateinit var tvAddToThePlaylist: TextView
    private lateinit var tvNameSongDialogBottom: TextView
    private lateinit var mContext: Context
    private lateinit var song: Song
    private lateinit var songPlay: Song
    private val songViewModel by viewModels<SongViewModel>()
    private val songQueueDirector = SongQueueDirector()
    private val buildSongQueue = BuildSongQueue()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentDiaLogBottomSongBinding =
        FragmentDiaLogBottomSongBinding.inflate(layoutInflater, container, false)

    override fun initView() {
        super.initView()
        tvNextPlay = binding.tvNextPlay
        tvNameSongDialogBottom = binding.tvNameSongDialogBottom
        tvAddToThePlaylist=binding.tvAddToThePlaylist
    }

    override fun setupView() {
        super.setupView()
        getDataSong()
    }

    override fun setupListen() {
        super.setupListen()
        onclick()
        tvAddToThePlaylist.setOnClickListener {
            val diaLogPlaylistBottomFragment = DiaLogPlaylistBottomFragment()
            val songJson = Gson().toJson(song)
            val bundle = Bundle()
            bundle.putString(Constant.SEND_SONG_DIALOG_BOTTOM, songJson)
            diaLogPlaylistBottomFragment.arguments = bundle
            singletonApp.activeFragment?.childFragmentManager?.let { fragmentManager ->
                diaLogPlaylistBottomFragment.show(
                    fragmentManager, "DiaLogPlaylistBottomFragment")
            }
            dismiss()
        }
    }

    private fun getDataSong() {
        val songJson = arguments?.getString(Constant.SEND_SONG_DIALOG_BOTTOM) ?: Gson().toJson(
            SongBuilder().getResult()
        )
        val songPlayJson = arguments?.getString(Constant.SEND_SONG_PLAY_DIALOG_BOTTOM)
            ?: Gson().toJson(SongBuilder().getResult())
        song = Gson().fromJson(songJson, Song::class.java)
        songPlay = Gson().fromJson(songPlayJson, Song::class.java)
        tvNameSongDialogBottom.text = song.title
    }

    private fun onclick() {
        tvNextPlay.setOnClickListener {
            // Check if the song already exists in the list
            val songExists = singletonApp.listSongsPlay.any { it.filePath == song.filePath }
            if (!songExists) {
                // Song doesn't exist, so add it to the list
            } else {
                singletonApp.listSongsPlay.remove(song)
            }
            val indexSongPlay = singletonApp.listSongsPlay.indexOf(singletonApp.songPlay) + 1
            singletonApp.listSongsPlay.add(indexSongPlay, song)
            mContext.lSuccessToast("Song added successfully!")
            songQueueDirector.construct(buildSongQueue, singletonApp.listSongsPlay, songPlay)
            if (songPlay.filePath.isNotEmpty()) {
                songViewModel.updateSongQueue(buildSongQueue.getResult())
            } else {
                songViewModel.insertSongQueue(buildSongQueue.getResult())
            }
            // Dismiss the dialog
            dismiss()
        }
    }

}