package com.remotetechs.musicapp.presentation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.domain.model.Album;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0018B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\t2\n\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u001c\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0016J\u0014\u0010\u0017\u001a\u00020\t2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R(\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u0019"}, d2 = {"Lcom/remotetechs/musicapp/presentation/ui/adapter/AlbumBottomAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/remotetechs/musicapp/presentation/ui/adapter/AlbumBottomAdapter$ViewHolder;", "()V", "listBottomAlbum", "", "Lcom/haidragon/musicapp/domain/model/Album;", "onClickItem", "Lkotlin/Function1;", "", "getOnClickItem", "()Lkotlin/jvm/functions/Function1;", "setOnClickItem", "(Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setListBottomAlbum", "ViewHolder", "app_debug"})
public final class AlbumBottomAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.remotetechs.musicapp.presentation.ui.adapter.AlbumBottomAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.haidragon.musicapp.domain.model.Album> listBottomAlbum;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super com.haidragon.musicapp.domain.model.Album, kotlin.Unit> onClickItem;
    
    public AlbumBottomAdapter() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function1<com.haidragon.musicapp.domain.model.Album, kotlin.Unit> getOnClickItem() {
        return null;
    }
    
    public final void setOnClickItem(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super com.haidragon.musicapp.domain.model.Album, kotlin.Unit> p0) {
    }
    
    public final void setListBottomAlbum(@org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Album> listBottomAlbum) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.remotetechs.musicapp.presentation.ui.adapter.AlbumBottomAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.remotetechs.musicapp.presentation.ui.adapter.AlbumBottomAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/remotetechs/musicapp/presentation/ui/adapter/AlbumBottomAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/remotetechs/musicapp/presentation/ui/adapter/AlbumBottomAdapter;Landroid/view/View;)V", "imgAddBottom", "Landroid/widget/ImageView;", "item", "Lcom/haidragon/musicapp/domain/model/Album;", "getItem", "()Lcom/haidragon/musicapp/domain/model/Album;", "setItem", "(Lcom/haidragon/musicapp/domain/model/Album;)V", "tvBottomAlbum", "Landroid/widget/TextView;", "bind", "", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvBottomAlbum = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView imgAddBottom = null;
        @org.jetbrains.annotations.Nullable
        private com.haidragon.musicapp.domain.model.Album item;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.haidragon.musicapp.domain.model.Album getItem() {
            return null;
        }
        
        public final void setItem(@org.jetbrains.annotations.Nullable
        com.haidragon.musicapp.domain.model.Album p0) {
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.haidragon.musicapp.domain.model.Album item) {
        }
    }
}