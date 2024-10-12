package com.haidragon.musicapp.presentation.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.databinding.ActivityMainBinding;
import com.haidragon.musicapp.presentation.base.BaseActivity;
import com.haidragon.musicapp.presentation.ui.fragment.AlbumFragment;
import com.haidragon.musicapp.presentation.ui.fragment.FavouriteFragment;
import com.haidragon.musicapp.presentation.ui.fragment.HomeFragment;
import com.haidragon.musicapp.presentation.ui.fragment.PlayFragment;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.service.MusicService;
import com.haidragon.musicapp.service.MusicServiceResources;
import com.haidragon.musicapp.presentation.utils.Constant;
import com.haidragon.musicapp.presentation.utils.FragmentManager;
import com.haidragon.musicapp.presentation.utils.OnBlackListener;
import com.haidragon.musicapp.presentation.utils.SingletonApp;
import com.haidragon.musicapp.presentation.utils.UpdateListSong;
import com.haidragon.musicapp.presentation.utils.UtilsAnimation;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u0000 =2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001=B\u0005\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u0007H\u0016J\u0010\u0010(\u001a\u00020\u00022\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020&H\u0002J\b\u0010,\u001a\u00020&H\u0016J\b\u0010-\u001a\u00020&H\u0016J\b\u0010.\u001a\u00020&H\u0002J\b\u0010/\u001a\u00020&H\u0016J\b\u00100\u001a\u00020&H\u0016J\b\u00101\u001a\u00020&H\u0014J\b\u00102\u001a\u00020&H\u0002J\u0010\u00103\u001a\u00020&2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u00104\u001a\u00020&H\u0002J\b\u00105\u001a\u00020&H\u0002J\b\u00106\u001a\u00020&H\u0016J\b\u00107\u001a\u00020&H\u0016J\b\u00108\u001a\u00020&H\u0002J\u0010\u00109\u001a\u00020&2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010:\u001a\u00020&H\u0016J\b\u0010;\u001a\u00020&H\u0016J\b\u0010<\u001a\u00020&H\u0016R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00a8\u0006>"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/activity/HomeActivity;", "Lcom/haidragon/musicapp/presentation/base/BaseActivity;", "Lcom/haidragon/musicapp/databinding/ActivityMainBinding;", "Lcom/haidragon/musicapp/service/MusicServiceResources;", "Lcom/haidragon/musicapp/presentation/utils/OnBlackListener;", "()V", "action", "", "Ljava/lang/Integer;", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "doubleFinish", "isPlay", "", "Ljava/lang/Boolean;", "singletonApp", "Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "getSingletonApp", "()Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "setSingletonApp", "(Lcom/haidragon/musicapp/presentation/utils/SingletonApp;)V", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "songViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "getSongViewModel", "()Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "songViewModel$delegate", "Lkotlin/Lazy;", "tvExit", "Landroid/widget/TextView;", "updateListSong", "Lcom/haidragon/musicapp/presentation/utils/UpdateListSong;", "getUpdateListSong", "()Lcom/haidragon/musicapp/presentation/utils/UpdateListSong;", "setUpdateListSong", "(Lcom/haidragon/musicapp/presentation/utils/UpdateListSong;)V", "OnBlack", "", "position", "getActivityViewBinding", "inflater", "Landroid/view/LayoutInflater;", "getDataService", "gonMenu", "goneActionPlay", "hideSystemUI", "initUI", "observeViewModel", "onDestroy", "reloadSong", "sendToService", "setLayoutMusic", "setStatusPause", "setupView", "setupViewModel", "showLayout", "starService", "stopService", "visibleActionPlay", "visibleMenu", "Companion", "app_debug"})
public final class HomeActivity extends com.haidragon.musicapp.presentation.base.BaseActivity<com.haidragon.musicapp.databinding.ActivityMainBinding> implements com.haidragon.musicapp.service.MusicServiceResources, com.haidragon.musicapp.presentation.utils.OnBlackListener {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_DATA_SONG = "data_song";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SEND_SERVICE = "key_send_service";
    private static int colorBackground = 0;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.domain.model.Song song;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.presentation.utils.UpdateListSong updateListSong;
    @org.jetbrains.annotations.Nullable
    private java.lang.Boolean isPlay;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer action;
    @org.jetbrains.annotations.Nullable
    private android.content.BroadcastReceiver broadcastReceiver;
    private int doubleFinish = 0;
    private android.widget.TextView tvExit;
    @javax.inject.Inject
    public com.haidragon.musicapp.presentation.utils.SingletonApp singletonApp;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy songViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.presentation.ui.activity.HomeActivity.Companion Companion = null;
    
    public HomeActivity() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.databinding.ActivityMainBinding getActivityViewBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.haidragon.musicapp.presentation.utils.UpdateListSong getUpdateListSong() {
        return null;
    }
    
    public final void setUpdateListSong(@org.jetbrains.annotations.Nullable
    com.haidragon.musicapp.presentation.utils.UpdateListSong p0) {
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
    public void setupView() {
    }
    
    private final void hideSystemUI() {
    }
    
    @java.lang.Override
    public void initUI() {
    }
    
    @java.lang.Override
    public void OnBlack(int position) {
    }
    
    private final void reloadSong() {
    }
    
    @java.lang.Override
    public void observeViewModel() {
    }
    
    @java.lang.Override
    public void setupViewModel() {
    }
    
    private final void getDataService() {
    }
    
    private final void setLayoutMusic() {
    }
    
    private final void setStatusPause() {
    }
    
    private final void showLayout() {
    }
    
    private final void sendToService(int action) {
    }
    
    @java.lang.Override
    public void starService(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song) {
    }
    
    @java.lang.Override
    public void stopService() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    @java.lang.Override
    public void gonMenu() {
    }
    
    @java.lang.Override
    public void visibleMenu() {
    }
    
    @java.lang.Override
    public void goneActionPlay() {
    }
    
    @java.lang.Override
    public void visibleActionPlay() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/activity/HomeActivity$Companion;", "", "()V", "KEY_DATA_SONG", "", "KEY_SEND_SERVICE", "colorBackground", "", "getColorBackground", "()I", "setColorBackground", "(I)V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final int getColorBackground() {
            return 0;
        }
        
        public final void setColorBackground(int p0) {
        }
    }
}