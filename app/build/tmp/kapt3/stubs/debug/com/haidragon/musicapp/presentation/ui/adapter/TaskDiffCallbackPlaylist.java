package com.haidragon.musicapp.presentation.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DiffUtil;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.databinding.ItemPlaylistBinding;
import com.haidragon.musicapp.presentation.base.BaseListAdapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/adapter/TaskDiffCallbackPlaylist;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/haidragon/musicapp/domain/model/Playlist;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
public final class TaskDiffCallbackPlaylist extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.haidragon.musicapp.domain.model.Playlist> {
    
    public TaskDiffCallbackPlaylist() {
        super();
    }
    
    @java.lang.Override
    public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Playlist oldItem, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Playlist newItem) {
        return false;
    }
    
    @java.lang.Override
    public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Playlist oldItem, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Playlist newItem) {
        return false;
    }
}