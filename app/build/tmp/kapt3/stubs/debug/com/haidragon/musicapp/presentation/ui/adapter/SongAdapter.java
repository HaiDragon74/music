package com.haidragon.musicapp.presentation.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.haidragon.musicapp.domain.model.Song;
import androidx.recyclerview.widget.DiffUtil;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.databinding.ItemSongBinding;
import com.haidragon.musicapp.presentation.utils.SingletonApp;
import com.haidragon.musicapp.presentation.utils.UtilsImage;
import com.haidragon.musicapp.presentation.base.BaseListAdapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001#B\u0005\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0018\u001a\u00020\t2\n\u0010\u0019\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J\u001c\u0010\u001c\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0006H\u0016J\u000e\u0010 \u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R(\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR.\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0002X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/adapter/SongAdapter;", "Lcom/haidragon/musicapp/presentation/base/BaseListAdapter;", "Lcom/haidragon/musicapp/domain/model/Song;", "Lcom/haidragon/musicapp/presentation/ui/adapter/SongAdapter$ViewHolder;", "()V", "adapterPositionAfter", "", "clickItem", "Lkotlin/Function1;", "", "getClickItem", "()Lkotlin/jvm/functions/Function1;", "setClickItem", "(Lkotlin/jvm/functions/Function1;)V", "clickMenu", "Lkotlin/Function2;", "Landroid/widget/ImageButton;", "getClickMenu", "()Lkotlin/jvm/functions/Function2;", "setClickMenu", "(Lkotlin/jvm/functions/Function2;)V", "singletonApp", "Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "songBefore", "bind", "holder", "item", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setSingletonApp", "updateSong", "songUpdate", "ViewHolder", "app_debug"})
public final class SongAdapter extends com.haidragon.musicapp.presentation.base.BaseListAdapter<com.haidragon.musicapp.domain.model.Song, com.haidragon.musicapp.presentation.ui.adapter.SongAdapter.ViewHolder> {
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super com.haidragon.musicapp.domain.model.Song, kotlin.Unit> clickItem;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function2<? super android.widget.ImageButton, ? super com.haidragon.musicapp.domain.model.Song, kotlin.Unit> clickMenu;
    @org.jetbrains.annotations.NotNull
    private com.haidragon.musicapp.presentation.utils.SingletonApp singletonApp;
    private int adapterPositionAfter = 0;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.domain.model.Song songBefore;
    
    public SongAdapter() {
        super(null);
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function1<com.haidragon.musicapp.domain.model.Song, kotlin.Unit> getClickItem() {
        return null;
    }
    
    public final void setClickItem(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super com.haidragon.musicapp.domain.model.Song, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function2<android.widget.ImageButton, com.haidragon.musicapp.domain.model.Song, kotlin.Unit> getClickMenu() {
        return null;
    }
    
    public final void setClickMenu(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function2<? super android.widget.ImageButton, ? super com.haidragon.musicapp.domain.model.Song, kotlin.Unit> p0) {
    }
    
    public final void setSingletonApp(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.SingletonApp singletonApp) {
    }
    
    public final void updateSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songUpdate) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.presentation.ui.adapter.SongAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void bind(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.ui.adapter.SongAdapter.ViewHolder holder, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song item, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/adapter/SongAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Lcom/haidragon/musicapp/databinding/ItemSongBinding;", "(Lcom/haidragon/musicapp/presentation/ui/adapter/SongAdapter;Lcom/haidragon/musicapp/databinding/ItemSongBinding;)V", "animationSongPlay", "Lcom/airbnb/lottie/LottieAnimationView;", "imgMenu", "Landroid/widget/ImageButton;", "imgSong", "Landroid/widget/ImageView;", "item", "Lcom/haidragon/musicapp/domain/model/Song;", "tvArtist", "Landroid/widget/TextView;", "tvTitle", "bind", "", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvTitle = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvArtist = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView imgSong = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton imgMenu = null;
        @org.jetbrains.annotations.NotNull
        private final com.airbnb.lottie.LottieAnimationView animationSongPlay = null;
        @org.jetbrains.annotations.Nullable
        private com.haidragon.musicapp.domain.model.Song item;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        com.haidragon.musicapp.databinding.ItemSongBinding itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.haidragon.musicapp.domain.model.Song item) {
        }
    }
}