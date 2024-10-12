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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0010\u001a\u00020\u00072\n\u0010\u0011\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001c\u0010\u0015\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014H\u0016R(\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001a"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/adapter/PlaylistAdapter;", "Lcom/haidragon/musicapp/presentation/base/BaseListAdapter;", "Lcom/haidragon/musicapp/domain/model/Playlist;", "Lcom/haidragon/musicapp/presentation/ui/adapter/PlaylistAdapter$ViewHolder;", "()V", "clickItem", "Lkotlin/Function1;", "", "getClickItem", "()Lkotlin/jvm/functions/Function1;", "setClickItem", "(Lkotlin/jvm/functions/Function1;)V", "menu", "Landroid/widget/ImageButton;", "getMenu", "setMenu", "bind", "holder", "item", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
public final class PlaylistAdapter extends com.haidragon.musicapp.presentation.base.BaseListAdapter<com.haidragon.musicapp.domain.model.Playlist, com.haidragon.musicapp.presentation.ui.adapter.PlaylistAdapter.ViewHolder> {
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super com.haidragon.musicapp.domain.model.Playlist, kotlin.Unit> clickItem;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super android.widget.ImageButton, kotlin.Unit> menu;
    
    public PlaylistAdapter() {
        super(null);
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function1<com.haidragon.musicapp.domain.model.Playlist, kotlin.Unit> getClickItem() {
        return null;
    }
    
    public final void setClickItem(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super com.haidragon.musicapp.domain.model.Playlist, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function1<android.widget.ImageButton, kotlin.Unit> getMenu() {
        return null;
    }
    
    public final void setMenu(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super android.widget.ImageButton, kotlin.Unit> p0) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.presentation.ui.adapter.PlaylistAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void bind(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.ui.adapter.PlaylistAdapter.ViewHolder holder, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Playlist item, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/adapter/PlaylistAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Lcom/haidragon/musicapp/databinding/ItemPlaylistBinding;", "(Lcom/haidragon/musicapp/presentation/ui/adapter/PlaylistAdapter;Lcom/haidragon/musicapp/databinding/ItemPlaylistBinding;)V", "imgItem", "Landroid/widget/ImageView;", "imgMenu", "Landroid/widget/ImageButton;", "item", "Lcom/haidragon/musicapp/domain/model/Playlist;", "tvNumberSong", "Landroid/widget/TextView;", "tvTitle", "bind", "", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvTitle = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton imgMenu = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView imgItem = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvNumberSong = null;
        @org.jetbrains.annotations.Nullable
        private com.haidragon.musicapp.domain.model.Playlist item;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        com.haidragon.musicapp.databinding.ItemPlaylistBinding itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.haidragon.musicapp.domain.model.Playlist item) {
        }
    }
}