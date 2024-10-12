package com.remotetechs.musicapp.presentation.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import androidx.recyclerview.widget.DiffUtil
import com.haidragon.musicapp.R
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.databinding.ItemSongMultiTypeBinding
import com.haidragon.musicapp.presentation.utils.SingletonApp
import com.haidragon.musicapp.presentation.base.BaseListAdapter

class SongInPlayListAdapter :
    BaseListAdapter<Song, SongInPlayListAdapter.ViewHolder>(TaskDiffCallbackSongRecently()) {
    var clickItem: ((Song) -> Unit)? = null
    private var singletonApp= SingletonApp()
    private var adapterPositionAfter=0
    private var isCheckShowNumberPlaying=false
    fun setIsCheckShowNumberPlaying(isCheckShowNumberPlaying: Boolean){
        this.isCheckShowNumberPlaying=isCheckShowNumberPlaying
    }

    inner class ViewHolder(itemView: ItemSongMultiTypeBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val tvTitle=itemView.tvTitle
        private val tvArtist=itemView.tvArtist
        private val imgSong=itemView.imgSong
        private val imgMenu=itemView.imgMenu
        private val cvSongHome=itemView.cvSongHome
        private val animationSongPlay=itemView.animationSongPlay
        private var item: Song? = null
        init {
            itemView.root.setOnClickListener {
                item?.let { clickItem?.invoke(it) }
                notifyItemChanged(adapterPosition)
                notifyItemChanged(adapterPositionAfter)
                adapterPositionAfter=adapterPosition
            }
            imgMenu.setOnClickListener {
                val sonJson = Gson().toJson(item)
                val bundle = Bundle()
                bundle.putString("key_song_to_album", sonJson)
            }
        }
        fun bind(item: Song) {
            this.item = item
            if (isCheckShowNumberPlaying){
                val marginInDp = 24
                val marginInPx = (marginInDp * itemView.resources.displayMetrics.density).toInt()
                val layoutParams = cvSongHome.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.bottomMargin = marginInPx
                cvSongHome.layoutParams = layoutParams
            }else{
                val layoutParams = cvSongHome.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.bottomMargin = 0
                cvSongHome.layoutParams = layoutParams
            }
            if (item.coverArt.isEmpty()) {
                imgSong.setImageResource(R.drawable.ic_music)
            } else
                Glide.with(itemView.context).load(item.coverArt).into(imgSong)
            if (singletonApp.songPlay?.title==item.title && singletonApp.isPlaying){
                animationSongPlay.visibility=View.VISIBLE
            }else{
                animationSongPlay.visibility=View.GONE
            }
            tvTitle.text=item.title
            tvArtist.text=item.artist
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemSongMultiTypeBinding.inflate(inflater,parent, false)
        return ViewHolder(view)
    }

    override fun bind(holder: ViewHolder, item: Song, position: Int) {
        holder.bind(item)
    }
}

class TaskDiffCallbackSongRecently : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id == newItem.id
    }
}
