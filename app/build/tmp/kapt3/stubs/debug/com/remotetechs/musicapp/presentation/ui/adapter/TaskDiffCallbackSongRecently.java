package com.remotetechs.musicapp.presentation.ui.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import androidx.recyclerview.widget.DiffUtil;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.databinding.ItemSongMultiTypeBinding;
import com.haidragon.musicapp.presentation.utils.SingletonApp;
import com.haidragon.musicapp.presentation.base.BaseListAdapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/remotetechs/musicapp/presentation/ui/adapter/TaskDiffCallbackSongRecently;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/haidragon/musicapp/domain/model/Song;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
public final class TaskDiffCallbackSongRecently extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.haidragon.musicapp.domain.model.Song> {
    
    public TaskDiffCallbackSongRecently() {
        super();
    }
    
    @java.lang.Override
    public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song oldItem, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song newItem) {
        return false;
    }
    
    @java.lang.Override
    public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song oldItem, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song newItem) {
        return false;
    }
}