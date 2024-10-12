package com.haidragon.musicapp.presentation.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.haidragon.musicapp.presentation.ui.activity.PlayMusicActivity;
import com.haidragon.musicapp.presentation.ui.adapter.SongStorageAdapter;
import com.haidragon.musicapp.presentation.base.BaseFragment;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.databinding.FragmentSongInAlbumBinding;
import com.haidragon.musicapp.service.MusicServiceResources;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001 B\u0005\u00a2\u0006\u0002\u0010\u0003J$\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0019H\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010\u001f\u001a\u00020\u0019H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006!"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/SongInAlbumFragment;", "Lcom/haidragon/musicapp/presentation/base/BaseFragment;", "Lcom/haidragon/musicapp/databinding/FragmentSongInAlbumBinding;", "()V", "bundle", "Landroid/os/Bundle;", "musicServiceResources", "Lcom/haidragon/musicapp/service/MusicServiceResources;", "nameAlbum", "", "songStorageAdapter", "Lcom/haidragon/musicapp/presentation/ui/adapter/SongStorageAdapter;", "songViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "getSongViewModel", "()Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "songViewModel$delegate", "Lkotlin/Lazy;", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "onAttach", "", "context", "Landroid/content/Context;", "onBack", "onItemClickRecycler", "setDataRecycler", "setUpView", "Companion", "app_debug"})
public final class SongInAlbumFragment extends com.haidragon.musicapp.presentation.base.BaseFragment<com.haidragon.musicapp.databinding.FragmentSongInAlbumBinding> {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SONG_TO_ALBUM = "key_song_to_album";
    private android.os.Bundle bundle;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.service.MusicServiceResources musicServiceResources;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy songViewModel$delegate = null;
    private com.haidragon.musicapp.presentation.ui.adapter.SongStorageAdapter songStorageAdapter;
    @org.jetbrains.annotations.Nullable
    private java.lang.String nameAlbum;
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.presentation.ui.fragment.SongInAlbumFragment.Companion Companion = null;
    
    public SongInAlbumFragment() {
        super();
    }
    
    private final com.haidragon.musicapp.presentation.viewmodel.SongViewModel getSongViewModel() {
        return null;
    }
    
    @java.lang.Override
    public void onAttach(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.databinding.FragmentSongInAlbumBinding getViewBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void setUpView() {
    }
    
    private final void onItemClickRecycler() {
    }
    
    private final void setDataRecycler() {
    }
    
    private final void onBack() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/SongInAlbumFragment$Companion;", "", "()V", "KEY_SONG_TO_ALBUM", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}