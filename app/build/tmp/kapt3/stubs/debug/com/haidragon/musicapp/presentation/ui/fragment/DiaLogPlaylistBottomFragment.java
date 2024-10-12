package com.haidragon.musicapp.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.databinding.FragmentDiaLogBottomPlaylistBinding;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.databinding.FragmentDiaLogBottomSongBinding;
import com.haidragon.musicapp.presentation.base.BaseBottomFragment;
import com.haidragon.musicapp.presentation.ui.adapter.PlaylistAdapter;
import com.haidragon.musicapp.presentation.utils.Constant;
import com.haidragon.musicapp.presentation.utils.SingletonApp;
import com.haidragon.musicapp.presentation.utils.SongQueueDirector;
import com.haidragon.musicapp.presentation.utils.build.BuildSongQueue;
import com.haidragon.musicapp.presentation.utils.build.SongBuilder;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;
import com.remotetechs.musicapp.presentation.ui.adapter.SongInPlayListAdapter;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J$\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0005H\u0016J\b\u0010\u001c\u001a\u00020\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u0018H\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001f"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/DiaLogPlaylistBottomFragment;", "Lcom/haidragon/musicapp/presentation/base/BaseBottomFragment;", "Lcom/haidragon/musicapp/databinding/FragmentDiaLogBottomPlaylistBinding;", "()V", "mcontext", "Landroid/content/Context;", "playListAdapter", "Lcom/haidragon/musicapp/presentation/ui/adapter/PlaylistAdapter;", "recyclerPlaylistBottom", "Landroidx/recyclerview/widget/RecyclerView;", "songViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "getSongViewModel", "()Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "songViewModel$delegate", "Lkotlin/Lazy;", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "initView", "", "observeViewModel", "onAttach", "context", "setupListen", "setupView", "setupViewModel", "app_debug"})
public final class DiaLogPlaylistBottomFragment extends com.haidragon.musicapp.presentation.base.BaseBottomFragment<com.haidragon.musicapp.databinding.FragmentDiaLogBottomPlaylistBinding> {
    private com.haidragon.musicapp.presentation.ui.adapter.PlaylistAdapter playListAdapter;
    private android.content.Context mcontext;
    private androidx.recyclerview.widget.RecyclerView recyclerPlaylistBottom;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy songViewModel$delegate = null;
    
    public DiaLogPlaylistBottomFragment() {
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
    public com.haidragon.musicapp.databinding.FragmentDiaLogBottomPlaylistBinding getViewBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void initView() {
    }
    
    @java.lang.Override
    public void setupView() {
    }
    
    @java.lang.Override
    public void setupListen() {
    }
    
    @java.lang.Override
    public void observeViewModel() {
    }
    
    @java.lang.Override
    public void setupViewModel() {
    }
}