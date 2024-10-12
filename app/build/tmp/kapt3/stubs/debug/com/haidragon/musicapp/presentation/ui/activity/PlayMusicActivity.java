package com.haidragon.musicapp.presentation.ui.activity;

import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.SeekBar;
import androidx.activity.OnBackPressedCallback;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.presentation.base.BaseActivity;
import com.haidragon.musicapp.presentation.ui.fragment.DiaLogSongBottomFragment;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.databinding.ActivityPlayMusicBinding;
import com.haidragon.musicapp.service.MusicService;
import com.haidragon.musicapp.presentation.utils.Constant;
import com.haidragon.musicapp.presentation.utils.UtilsImage;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.Locale;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 *2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001*B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\b\u0010 \u001a\u00020\u001dH\u0014J\b\u0010!\u001a\u00020\u001dH\u0002J\b\u0010\"\u001a\u00020\u001dH\u0002J\b\u0010#\u001a\u00020\u001dH\u0002J\u001f\u0010$\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u0002\u00a2\u0006\u0002\u0010%J\b\u0010&\u001a\u00020\u001dH\u0002J\b\u0010\'\u001a\u00020\u001dH\u0002J\b\u0010(\u001a\u00020\u001dH\u0016J\b\u0010)\u001a\u00020\u001dH\u0002R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006+"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/activity/PlayMusicActivity;", "Lcom/haidragon/musicapp/presentation/base/BaseActivity;", "Lcom/haidragon/musicapp/databinding/ActivityPlayMusicBinding;", "()V", "action", "", "Ljava/lang/Integer;", "animator", "Landroid/animation/ValueAnimator;", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "isPlay", "", "Ljava/lang/Boolean;", "position", "seekBar", "Landroid/widget/SeekBar;", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "songViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "getSongViewModel", "()Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "songViewModel$delegate", "Lkotlin/Lazy;", "getActivityViewBinding", "inflater", "Landroid/view/LayoutInflater;", "getDataService", "", "hideSystemUI", "onBack", "onDestroy", "reloadMusic", "rotateImage", "seekBarMusic", "sendToService", "(ILjava/lang/Integer;)V", "setLayoutPlayMusic", "setStatusPause", "setupView", "showLayout", "Companion", "app_debug"})
public final class PlayMusicActivity extends com.haidragon.musicapp.presentation.base.BaseActivity<com.haidragon.musicapp.databinding.ActivityPlayMusicBinding> {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SEND_SERVICE = "key_send_service";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_DATA_SONG = "data_song";
    @org.jetbrains.annotations.Nullable
    private android.animation.ValueAnimator animator;
    private android.widget.SeekBar seekBar;
    private int position = 0;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy songViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.domain.model.Song song;
    @org.jetbrains.annotations.Nullable
    private java.lang.Boolean isPlay;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer action;
    @org.jetbrains.annotations.Nullable
    private android.content.BroadcastReceiver broadcastReceiver;
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.presentation.ui.activity.PlayMusicActivity.Companion Companion = null;
    
    public PlayMusicActivity() {
        super();
    }
    
    private final com.haidragon.musicapp.presentation.viewmodel.SongViewModel getSongViewModel() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.databinding.ActivityPlayMusicBinding getActivityViewBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater) {
        return null;
    }
    
    @java.lang.Override
    public void setupView() {
    }
    
    private final void hideSystemUI() {
    }
    
    private final void reloadMusic() {
    }
    
    private final void getDataService() {
    }
    
    private final void setLayoutPlayMusic() {
    }
    
    private final void setStatusPause() {
    }
    
    private final void showLayout() {
    }
    
    private final void seekBarMusic() {
    }
    
    private final void rotateImage() {
    }
    
    private final void sendToService(int action, java.lang.Integer position) {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    private final void onBack() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/activity/PlayMusicActivity$Companion;", "", "()V", "KEY_DATA_SONG", "", "KEY_SEND_SERVICE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}