package com.haidragon.musicapp.presentation.ui.fragment;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.gson.Gson;
import com.haidragon.musicapp.presentation.ui.adapter.SongAdapter;
import com.haidragon.musicapp.presentation.base.BaseFragment;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.databinding.FragmentSongBinding;
import com.haidragon.musicapp.presentation.ui.activity.HomeActivity;
import com.haidragon.musicapp.service.MusicServiceResources;
import com.haidragon.musicapp.presentation.utils.Constant;
import com.haidragon.musicapp.presentation.utils.FragmentManager;
import com.haidragon.musicapp.presentation.utils.OnBlackListener;
import com.haidragon.musicapp.presentation.utils.UtilsSong;
import com.haidragon.musicapp.presentation.utils.PlaylistDirector;
import com.haidragon.musicapp.presentation.utils.SharedPref;
import com.haidragon.musicapp.presentation.utils.SingletonApp;
import com.haidragon.musicapp.presentation.utils.SongQueueDirector;
import com.haidragon.musicapp.presentation.utils.UpdateListSong;
import com.haidragon.musicapp.presentation.utils.UtilsAnimation;
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylistRecentlyPlayed;
import com.haidragon.musicapp.presentation.utils.build.BuildSongQueue;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010-\u001a\u00020.H\u0002J$\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u0001032\b\u00104\u001a\u0004\u0018\u000105H\u0016J\b\u00106\u001a\u00020.H\u0016J\u0010\u00107\u001a\u00020.2\u0006\u00108\u001a\u000209H\u0016J\b\u0010:\u001a\u00020.H\u0016J\u0010\u0010;\u001a\u00020.2\u0006\u0010<\u001a\u00020\u0012H\u0016J\b\u0010=\u001a\u00020.H\u0002J\b\u0010>\u001a\u00020.H\u0016J\b\u0010?\u001a\u00020.H\u0002J\b\u0010@\u001a\u00020.H\u0002J\b\u0010A\u001a\u00020.H\u0016J\b\u0010B\u001a\u00020.H\u0016J\b\u0010C\u001a\u00020.H\u0016J\u0010\u0010D\u001a\u00020.2\u0006\u00108\u001a\u000209H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020&8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b\'\u0010(R\u000e\u0010+\u001a\u00020,X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006E"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/SongFragment;", "Lcom/haidragon/musicapp/presentation/base/BaseFragment;", "Lcom/haidragon/musicapp/databinding/FragmentSongBinding;", "Lcom/haidragon/musicapp/presentation/utils/UpdateListSong;", "()V", "activity", "Landroidx/fragment/app/FragmentActivity;", "buildPlaylistRecentlyPlayed", "Lcom/haidragon/musicapp/presentation/utils/build/BuildPlaylistRecentlyPlayed;", "buildSongQueue", "Lcom/haidragon/musicapp/presentation/utils/build/BuildSongQueue;", "directorPlaylist", "Lcom/haidragon/musicapp/presentation/utils/PlaylistDirector;", "frameLayoutPlayMusic", "Landroid/widget/FrameLayout;", "frameLayoutRecentSong", "frameLayoutShuffle", "mContext", "Landroid/content/Context;", "musicServiceResources", "Lcom/haidragon/musicapp/service/MusicServiceResources;", "onBlackListener", "Lcom/haidragon/musicapp/presentation/utils/OnBlackListener;", "sharedPref", "Lcom/haidragon/musicapp/presentation/utils/SharedPref;", "sharedPreferences", "Landroid/content/SharedPreferences;", "singletonApp", "Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "getSingletonApp", "()Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "setSingletonApp", "(Lcom/haidragon/musicapp/presentation/utils/SingletonApp;)V", "songAdapter", "Lcom/haidragon/musicapp/presentation/ui/adapter/SongAdapter;", "songQueueDirector", "Lcom/haidragon/musicapp/presentation/utils/SongQueueDirector;", "songViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "getSongViewModel", "()Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "songViewModel$delegate", "Lkotlin/Lazy;", "swipeRefreshLayoutSong", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "checkStoragePermission", "", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "initUI", "notifyDataChanged", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "observeViewModel", "onAttach", "context", "onClickItemRecycler", "onResume", "playSongLastOrRandom", "refreshData", "setUpView", "setupListen", "setupViewModel", "startServiceBySong", "app_debug"})
public final class SongFragment extends com.haidragon.musicapp.presentation.base.BaseFragment<com.haidragon.musicapp.databinding.FragmentSongBinding> implements com.haidragon.musicapp.presentation.utils.UpdateListSong {
    private android.content.Context mContext;
    private com.haidragon.musicapp.presentation.ui.adapter.SongAdapter songAdapter;
    private com.haidragon.musicapp.presentation.utils.SharedPref sharedPref;
    private androidx.fragment.app.FragmentActivity activity;
    private android.widget.FrameLayout frameLayoutPlayMusic;
    private android.widget.FrameLayout frameLayoutShuffle;
    private android.widget.FrameLayout frameLayoutRecentSong;
    private androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefreshLayoutSong;
    private android.content.SharedPreferences sharedPreferences;
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.presentation.utils.PlaylistDirector directorPlaylist = null;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.presentation.utils.OnBlackListener onBlackListener;
    private com.haidragon.musicapp.presentation.utils.build.BuildPlaylistRecentlyPlayed buildPlaylistRecentlyPlayed;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.service.MusicServiceResources musicServiceResources;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy songViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.presentation.utils.SongQueueDirector songQueueDirector = null;
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.presentation.utils.build.BuildSongQueue buildSongQueue = null;
    @javax.inject.Inject
    public com.haidragon.musicapp.presentation.utils.SingletonApp singletonApp;
    
    public SongFragment() {
        super();
    }
    
    private final com.haidragon.musicapp.presentation.viewmodel.SongViewModel getSongViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.presentation.utils.SingletonApp getSingletonApp() {
        return null;
    }
    
    public final void setSingletonApp(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.SingletonApp p0) {
    }
    
    @java.lang.Override
    public void onAttach(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @java.lang.Override
    public void initUI() {
    }
    
    @java.lang.Override
    public void setupListen() {
    }
    
    private final void playSongLastOrRandom() {
    }
    
    private final void startServiceBySong(com.haidragon.musicapp.domain.model.Song song) {
    }
    
    private final void refreshData() {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.databinding.FragmentSongBinding getViewBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void setUpView() {
    }
    
    @java.lang.Override
    public void onResume() {
    }
    
    private final void checkStoragePermission() {
    }
    
    @java.lang.Override
    public void setupViewModel() {
    }
    
    @java.lang.Override
    public void observeViewModel() {
    }
    
    private final void onClickItemRecycler() {
    }
    
    @java.lang.Override
    public void notifyDataChanged(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song) {
    }
}