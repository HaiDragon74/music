package com.haidragon.musicapp.presentation.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u001c\u0010\u0016\u001a\u00020\t2\n\u0010\u0017\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u001c\u0010\u0019\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0015H\u0016J\u0014\u0010\u001d\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fR(\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/adapter/SongStorageAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/haidragon/musicapp/presentation/ui/adapter/SongStorageAdapter$ViewHolder;", "songViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "(Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;)V", "clickItem", "Lkotlin/Function1;", "Lcom/haidragon/musicapp/domain/model/Song;", "", "getClickItem", "()Lkotlin/jvm/functions/Function1;", "setClickItem", "(Lkotlin/jvm/functions/Function1;)V", "listSongStorage", "", "deleteSong", "context", "Landroid/content/Context;", "song", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setListSongStorage", "ViewHolder", "app_debug"})
public final class SongStorageAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.haidragon.musicapp.presentation.ui.adapter.SongStorageAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.presentation.viewmodel.SongViewModel songViewModel = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.haidragon.musicapp.domain.model.Song> listSongStorage;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super com.haidragon.musicapp.domain.model.Song, kotlin.Unit> clickItem;
    
    public SongStorageAdapter(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.viewmodel.SongViewModel songViewModel) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function1<com.haidragon.musicapp.domain.model.Song, kotlin.Unit> getClickItem() {
        return null;
    }
    
    public final void setClickItem(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super com.haidragon.musicapp.domain.model.Song, kotlin.Unit> p0) {
    }
    
    public final void setListSongStorage(@org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongStorage) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.presentation.ui.adapter.SongStorageAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.ui.adapter.SongStorageAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    private final void deleteSong(android.content.Context context, com.haidragon.musicapp.domain.model.Song song) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/adapter/SongStorageAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/haidragon/musicapp/presentation/ui/adapter/SongStorageAdapter;Landroid/view/View;)V", "imgDeleteSongStorage", "Landroid/widget/ImageView;", "imgSongStorage", "item", "Lcom/haidragon/musicapp/domain/model/Song;", "tvArtistSongStorage", "Landroid/widget/TextView;", "tvTitleSongStorage", "bind", "", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvTitleSongStorage = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvArtistSongStorage = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView imgSongStorage = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView imgDeleteSongStorage = null;
        @org.jetbrains.annotations.Nullable
        private com.haidragon.musicapp.domain.model.Song item;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.haidragon.musicapp.domain.model.Song item) {
        }
    }
}