package com.haidragon.musicapp.presentation.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haidragon.musicapp.R
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel


class SongStorageAdapter(private val songViewModel: SongViewModel) :
    RecyclerView.Adapter<SongStorageAdapter.ViewHolder>() {
    private var listSongStorage = mutableListOf<Song>()
    var clickItem: ((Song) -> Unit)? = null
    fun setListSongStorage(listSongStorage: MutableList<Song>) {
        this.listSongStorage = listSongStorage
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitleSongStorage: TextView = itemView.findViewById(R.id.tv_title_song_storage)
        private val tvArtistSongStorage: TextView =
            itemView.findViewById(R.id.tv_artist_song_storage)
        private val imgSongStorage: ImageView = itemView.findViewById(R.id.img_song_storage)
        private val imgDeleteSongStorage: ImageView =
            itemView.findViewById(R.id.img_delete_song_storage)
        private var item: Song? = null

        init {
            itemView.setOnClickListener {
                item?.let { clickItem?.invoke(it) }
            }
            imgDeleteSongStorage.setOnClickListener {
                deleteSong(itemView.context, item)
            }
        }

        fun bind(item: Song) {
            this.item = item
            tvTitleSongStorage.text = item.title
            tvArtistSongStorage.text = item.artist
            if (item.coverArt == null) {
                imgSongStorage.setImageResource(R.drawable.ic_music)
            } else
                Glide.with(itemView.context).load(item.coverArt).into(imgSongStorage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_song_storage, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSongStorage[position])
    }

    override fun getItemCount(): Int {
        return listSongStorage.size
    }

    private fun deleteSong(context: Context, song: Song?) {
        val diaLog = AlertDialog.Builder(context)
        diaLog.apply {
            setTitle("DELETE SONG")
            setMessage("Click yes to delete the song ${song?.title}")
            setPositiveButton("yes") { dialog, which ->
                song?.let { songViewModel.deleteSongByFilePath(it) }
            }
            setNegativeButton("no", null)
        }.show()
    }

}