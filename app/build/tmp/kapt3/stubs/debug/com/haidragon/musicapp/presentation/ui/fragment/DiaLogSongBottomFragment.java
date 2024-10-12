package com.haidragon.musicapp.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.databinding.FragmentDiaLogBottomSongBinding;
import com.haidragon.musicapp.presentation.base.BaseBottomFragment;
import com.haidragon.musicapp.presentation.utils.Constant;
import com.haidragon.musicapp.presentation.utils.SingletonApp;
import com.haidragon.musicapp.presentation.utils.SongQueueDirector;
import com.haidragon.musicapp.presentation.utils.build.BuildSongQueue;
import com.haidragon.musicapp.presentation.utils.build.SongBuilder;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001d\u001a\u00020\u001eH\u0002J$\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u001eH\u0016J\u0010\u0010\'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u0007H\u0016J\b\u0010)\u001a\u00020\u001eH\u0002J\b\u0010*\u001a\u00020\u001eH\u0016J\b\u0010+\u001a\u00020\u001eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/DiaLogSongBottomFragment;", "Lcom/haidragon/musicapp/presentation/base/BaseBottomFragment;", "Lcom/haidragon/musicapp/databinding/FragmentDiaLogBottomSongBinding;", "()V", "buildSongQueue", "Lcom/haidragon/musicapp/presentation/utils/build/BuildSongQueue;", "mContext", "Landroid/content/Context;", "singletonApp", "Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "getSingletonApp", "()Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "setSingletonApp", "(Lcom/haidragon/musicapp/presentation/utils/SingletonApp;)V", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "songPlay", "songQueueDirector", "Lcom/haidragon/musicapp/presentation/utils/SongQueueDirector;", "songViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "getSongViewModel", "()Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "songViewModel$delegate", "Lkotlin/Lazy;", "tvAddToThePlaylist", "Landroid/widget/TextView;", "tvNameSongDialogBottom", "tvNextPlay", "getDataSong", "", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "initView", "onAttach", "context", "onclick", "setupListen", "setupView", "app_debug"})
public final class DiaLogSongBottomFragment extends com.haidragon.musicapp.presentation.base.BaseBottomFragment<com.haidragon.musicapp.databinding.FragmentDiaLogBottomSongBinding> {
    @javax.inject.Inject
    public com.haidragon.musicapp.presentation.utils.SingletonApp singletonApp;
    private android.widget.TextView tvNextPlay;
    private android.widget.TextView tvAddToThePlaylist;
    private android.widget.TextView tvNameSongDialogBottom;
    private android.content.Context mContext;
    private com.haidragon.musicapp.domain.model.Song song;
    private com.haidragon.musicapp.domain.model.Song songPlay;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy songViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.presentation.utils.SongQueueDirector songQueueDirector = null;
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.presentation.utils.build.BuildSongQueue buildSongQueue = null;
    
    public DiaLogSongBottomFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.presentation.utils.SingletonApp getSingletonApp() {
        return null;
    }
    
    public final void setSingletonApp(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.SingletonApp p0) {
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
    public com.haidragon.musicapp.databinding.FragmentDiaLogBottomSongBinding getViewBinding(@org.jetbrains.annotations.NotNull
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
    
    private final void getDataSong() {
    }
    
    private final void onclick() {
    }
}