package com.haidragon.musicapp.presentation.ui.fragment;

import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.SeekBar;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.presentation.base.BaseFragment;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.databinding.FragmentPlayBinding;
import com.haidragon.musicapp.service.MusicService;
import com.haidragon.musicapp.service.MusicServiceResources;
import com.haidragon.musicapp.presentation.utils.Constant;
import com.haidragon.musicapp.presentation.utils.OnBlackListener;
import com.haidragon.musicapp.presentation.utils.UtilsAnimation;
import com.haidragon.musicapp.presentation.utils.UtilsImage;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.Locale;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0007\u0018\u0000 92\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00019B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010!\u001a\u00020\"H\u0002J$\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0010\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u0011H\u0016J\b\u0010,\u001a\u00020\"H\u0002J\b\u0010-\u001a\u00020\"H\u0016J\b\u0010.\u001a\u00020\"H\u0002J\b\u0010/\u001a\u00020\"H\u0002J\b\u00100\u001a\u00020\"H\u0002J\b\u00101\u001a\u00020\"H\u0002J\u001f\u00102\u001a\u00020\"2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u0002\u00a2\u0006\u0002\u00103J\b\u00104\u001a\u00020\"H\u0002J\b\u00105\u001a\u00020\"H\u0002J\b\u00106\u001a\u00020\"H\u0016J\b\u00107\u001a\u00020\"H\u0002J\b\u00108\u001a\u00020\"H\u0002R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006:"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/PlayFragment;", "Lcom/haidragon/musicapp/presentation/base/BaseFragment;", "Lcom/haidragon/musicapp/databinding/FragmentPlayBinding;", "()V", "action", "", "Ljava/lang/Integer;", "activity", "Landroidx/fragment/app/FragmentActivity;", "animator", "Landroid/animation/ValueAnimator;", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "isPlay", "", "Ljava/lang/Boolean;", "mContext", "Landroid/content/Context;", "musicServiceResources", "Lcom/haidragon/musicapp/service/MusicServiceResources;", "onBlackListener", "Lcom/haidragon/musicapp/presentation/utils/OnBlackListener;", "position", "seekBar", "Landroid/widget/SeekBar;", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "songViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "getSongViewModel", "()Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "songViewModel$delegate", "Lkotlin/Lazy;", "getDataService", "", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "onBack", "onDestroy", "reloadMusic", "removeCurrentFragment", "rotateImage", "seekBarMusic", "sendToService", "(ILjava/lang/Integer;)V", "setLayoutPlayMusic", "setStatusPause", "setUpView", "showLayout", "startAnimation", "Companion", "app_debug"})
public final class PlayFragment extends com.haidragon.musicapp.presentation.base.BaseFragment<com.haidragon.musicapp.databinding.FragmentPlayBinding> {
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
    private androidx.fragment.app.FragmentActivity activity;
    private android.content.Context mContext;
    @org.jetbrains.annotations.Nullable
    private android.content.BroadcastReceiver broadcastReceiver;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.presentation.utils.OnBlackListener onBlackListener;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.service.MusicServiceResources musicServiceResources;
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.presentation.ui.fragment.PlayFragment.Companion Companion = null;
    
    public PlayFragment() {
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
    public com.haidragon.musicapp.databinding.FragmentPlayBinding getViewBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void setUpView() {
    }
    
    private final void startAnimation() {
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
    public void onDestroy() {
    }
    
    private final void onBack() {
    }
    
    private final void removeCurrentFragment() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/PlayFragment$Companion;", "", "()V", "KEY_DATA_SONG", "", "KEY_SEND_SERVICE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}