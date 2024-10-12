package com.haidragon.musicapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.haidragon.musicapp.R
import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.databinding.ItemPlaylistBinding
import com.haidragon.musicapp.presentation.base.BaseListAdapter

class PlaylistAdapter :
    BaseListAdapter<Playlist, PlaylistAdapter.ViewHolder>(TaskDiffCallbackPlaylist()) {
    var clickItem: ((Playlist) -> Unit)? = null
    var menu: ((ImageButton) -> Unit)? = null
    inner class ViewHolder(itemView: ItemPlaylistBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val tvTitle=itemView.tvTitle
        private val imgMenu=itemView.imgMenu
        private val imgItem=itemView.imgSong
        private val tvNumberSong=itemView.tvNumberSong
        private var item: Playlist? = null
        init {
            itemView.root.setOnClickListener {
                item?.let { clickItem?.invoke(it) }
            }
            menu?.invoke(imgMenu)
        }
        fun bind(item: Playlist) {
            this.item=item
            val context=itemView.context
            tvTitle.text=item.name
            when(item.name){
                context.getString(R.string.favorite) -> imgItem.setImageResource(R.drawable.ic_favourite)
                context.getString(R.string.my_top_tracks) -> imgItem.setImageResource(R.drawable.ic_my_top_tracks)
                context.getString(R.string.recently_played) -> imgItem.setImageResource(R.drawable.ic_recently_played)
                context.getString(R.string.recently_added) -> imgItem.setImageResource(R.drawable.ic_recently_added)
            }
            val numberSong="${item.listSongInPlaylist.size} Songs"
            tvNumberSong.text= numberSong

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemPlaylistBinding.inflate(inflater,parent, false)
        return ViewHolder(view)
    }

    override fun bind(holder: ViewHolder, item: Playlist, position: Int) {
        holder.bind(item)
    }
}

class TaskDiffCallbackPlaylist : DiffUtil.ItemCallback<Playlist>() {
    override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
        return oldItem.id == newItem.id
    }
}
