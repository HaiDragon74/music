package com.remotetechs.musicapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haidragon.musicapp.R
import com.haidragon.musicapp.domain.model.Album

class AlbumAdapter() : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    private var listAlbum: MutableList<Album> = mutableListOf()
    var onClick: ((Album) -> Unit)? = null
    fun setListAlbum(listAlbum: MutableList<Album>) {
        this.listAlbum = listAlbum
        notifyDataSetChanged()
    }

    fun getListAlbum(): MutableList<Album> {
        return listAlbum
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_title_album)
        private var item: Album? = null

        init {
            itemView.setOnClickListener {
                item?.let { onClick?.invoke(it) }
            }
        }

        fun bind(item: Album) {
            this.item = item
            tvName.text = item.nameAlbum
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listAlbum[position])
    }

    override fun getItemCount(): Int {
        return listAlbum.size
    }
}