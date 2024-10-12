package com.haidragon.musicapp.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.remotetechs.musicapp.presentation.ui.adapter.AlbumAdapter;
import com.haidragon.musicapp.presentation.base.BaseFragment;
import com.haidragon.musicapp.domain.model.Album;
import com.haidragon.musicapp.databinding.FragmentAlbumBinding;
import com.haidragon.musicapp.service.MusicServiceResources;
import com.haidragon.musicapp.presentation.utils.OnBlackListener;
import com.haidragon.musicapp.presentation.viewmodel.AlbumViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J$\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0011H\u0002J\b\u0010\u001e\u001a\u00020\u0011H\u0002J\b\u0010\u001f\u001a\u00020\u0011H\u0016J\b\u0010 \u001a\u00020\u0011H\u0002J\b\u0010!\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/AlbumFragment;", "Lcom/haidragon/musicapp/presentation/base/BaseFragment;", "Lcom/haidragon/musicapp/databinding/FragmentAlbumBinding;", "()V", "albumAdapter", "Lcom/remotetechs/musicapp/presentation/ui/adapter/AlbumAdapter;", "albumViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/AlbumViewModel;", "getAlbumViewModel", "()Lcom/haidragon/musicapp/presentation/viewmodel/AlbumViewModel;", "albumViewModel$delegate", "Lkotlin/Lazy;", "musicServiceResources", "Lcom/haidragon/musicapp/service/MusicServiceResources;", "onBlackListener", "Lcom/haidragon/musicapp/presentation/utils/OnBlackListener;", "createAlbum", "", "deleteItemAlbum", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onBack", "onClickItemRecycler", "onResume", "setDataRecycler", "setUpView", "Companion", "app_debug"})
public final class AlbumFragment extends com.haidragon.musicapp.presentation.base.BaseFragment<com.haidragon.musicapp.databinding.FragmentAlbumBinding> {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SONG_TO_ALBUM = "key_song_to_album";
    private com.remotetechs.musicapp.presentation.ui.adapter.AlbumAdapter albumAdapter;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy albumViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.service.MusicServiceResources musicServiceResources;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.presentation.utils.OnBlackListener onBlackListener;
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.presentation.ui.fragment.AlbumFragment.Companion Companion = null;
    
    public AlbumFragment() {
        super();
    }
    
    private final com.haidragon.musicapp.presentation.viewmodel.AlbumViewModel getAlbumViewModel() {
        return null;
    }
    
    @java.lang.Override
    public void onAttach(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.databinding.FragmentAlbumBinding getViewBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void setUpView() {
    }
    
    private final void deleteItemAlbum() {
    }
    
    @java.lang.Override
    public void onResume() {
    }
    
    private final void onClickItemRecycler() {
    }
    
    private final void setDataRecycler() {
    }
    
    private final void createAlbum() {
    }
    
    private final void onBack() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/AlbumFragment$Companion;", "", "()V", "KEY_SONG_TO_ALBUM", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}