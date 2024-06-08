package com.remotetechs.musicapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.remotetechs.musicapp.R
import com.remotetechs.musicapp.model.Song


class SongAdapter() : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    private var listSong = mutableListOf<Song>()
    var clickItem: ((Song) -> Unit)? = null
    fun setListSong(listSong: MutableList<Song>) {
        this.listSong = listSong
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvArtist: TextView = itemView.findViewById(R.id.tv_artist)
        private val imgSong: ImageView = itemView.findViewById(R.id.img_song)
        private val imgenu: ImageView = itemView.findViewById(R.id.img_menu)
        private var item: Song? = null

        init {
            itemView.setOnClickListener {
                item?.let { clickItem?.invoke(it) }
            }
            imgenu.setOnClickListener {
                val sonJson = Gson().toJson(item)
                val bundle = Bundle()
                bundle.putString("key_song_to_album", sonJson)
                itemView.findNavController()
                    .navigate(R.id.action_homeFragment_to_diaLogBottomAlbumFragment, bundle)
            }
        }

        fun bind(item: Song) {
            this.item = item
            tvTitle.text = item.title
            tvArtist.text = item.artist
            if (item.coverArt == null) {
                imgSong.setImageResource(R.drawable.ic_img_item)
            } else
                Glide.with(itemView.context).load(item.coverArt).into(imgSong)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSong[position])
    }

    override fun getItemCount(): Int {
        return listSong.size
    }

}