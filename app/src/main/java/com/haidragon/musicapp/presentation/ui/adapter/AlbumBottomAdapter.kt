package com.remotetechs.musicapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haidragon.musicapp.R
import com.haidragon.musicapp.domain.model.Album

class AlbumBottomAdapter() : RecyclerView.Adapter<AlbumBottomAdapter.ViewHolder>() {
    private var listBottomAlbum: MutableList<Album> = mutableListOf()
    var onClickItem: ((Album) -> Unit)? = null
    fun setListBottomAlbum(listBottomAlbum: MutableList<Album>) {
        this.listBottomAlbum = listBottomAlbum
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvBottomAlbum: TextView = itemView.findViewById(R.id.tv_bottom_album)
        private val imgAddBottom: ImageView = itemView.findViewById(R.id.img_add_bottom)
        var item: Album? = null

        init {
            imgAddBottom.setOnClickListener {
                item?.let { onClickItem?.invoke(it) }
            }
        }

        fun bind(item: Album) {
            this.item = item
            tvBottomAlbum.text = item.nameAlbum
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bottom_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listBottomAlbum[position])
    }

    override fun getItemCount(): Int {
        return listBottomAlbum.size
    }
}