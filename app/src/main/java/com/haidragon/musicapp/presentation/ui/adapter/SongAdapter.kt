package com.haidragon.musicapp.presentation.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haidragon.musicapp.domain.model.Song
import androidx.recyclerview.widget.DiffUtil
import com.haidragon.musicapp.R
import com.haidragon.musicapp.databinding.ItemSongBinding
import com.haidragon.musicapp.presentation.utils.SingletonApp
import com.haidragon.musicapp.presentation.utils.UtilsImage
import com.haidragon.musicapp.presentation.base.BaseListAdapter

class SongAdapter :
    BaseListAdapter<Song, SongAdapter.ViewHolder>(TaskDiffCallbackSong()) {
    var clickItem: ((Song) -> Unit)? = null
    var clickMenu: ((ImageButton, Song) -> Unit)? = null
    private var singletonApp= SingletonApp()
    private var adapterPositionAfter=0
    private var songBefore:Song?=null
    fun setSingletonApp(singletonApp: SingletonApp){
        this.singletonApp=singletonApp
    }
    fun updateSong(songUpdate: Song) {
        singletonApp.isPlaying=true
        val positionUpdate = currentList.indexOfFirst { it.filePath == songUpdate.filePath }
        val positionBefore = currentList.indexOfFirst { it.filePath == songBefore?.filePath }
        notifyItemChanged(positionBefore)
        notifyItemChanged(positionUpdate)
    }
    inner class ViewHolder(itemView: ItemSongBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val tvTitle=itemView.tvTitle
        private val tvArtist=itemView.tvArtist
        private val imgSong=itemView.imgSong
        private val imgMenu=itemView.imgMenu
        private val animationSongPlay=itemView.animationSongPlay
        private var item: Song? = null
        init {
            itemView.root.setOnClickListener {
                val positionBefore = currentList.indexOfFirst { it.filePath == songBefore?.filePath }
                item?.let { clickItem?.invoke(it) }
                notifyItemChanged(adapterPosition)
                notifyItemChanged(positionBefore)
                notifyItemChanged(adapterPositionAfter)
                adapterPositionAfter=adapterPosition
            }
            imgMenu.setOnClickListener {
                item?.let { it1 -> clickMenu?.invoke(imgMenu, it1) }
            }
        }
        fun bind(item: Song) {
            this.item = item
            if (singletonApp.songPlay?.filePath==item.filePath && singletonApp.isPlaying){
                songBefore=item
                animationSongPlay.visibility=View.VISIBLE
                tvTitle.setTextColor(itemView.context.getColor(R.color.blue_title_song_play))
                tvArtist.setTextColor(itemView.context.getColor(R.color.blue_title_song_play))
            }else{
                animationSongPlay.visibility=View.GONE
            }
            val maxLengthTitle = 16
            val maxArtist = 22
            val titleText = item.title?:""
            val artistText = item.artist?:""
            tvTitle.text = if (titleText.length > maxLengthTitle) {
                "${titleText.take(maxLengthTitle)}...${"\u00A0".repeat(40)}"
            } else {
                "${titleText.take(maxLengthTitle)}${"\u00A0".repeat(40)}"
            }
            tvArtist.text = if (artistText.length > maxArtist) {
                "${artistText.take(maxArtist)}..."
            } else {
                artistText
            }
            if (item.coverArt.isEmpty()) {
                imgSong.setImageResource(R.drawable.bcr_click_item_song)
            } else {
                Glide.with(itemView.context).load(UtilsImage.base64ToBitmap(item.coverArt)).into(imgSong)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemSongBinding.inflate(inflater,parent, false)
        return ViewHolder(view)
    }

    override fun bind(holder: ViewHolder, item: Song, position: Int) {
        holder.bind(item)
    }
}

class TaskDiffCallbackSong : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id == newItem.id
    }
}
