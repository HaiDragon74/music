package com.haidragon.musicapp.service;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.presentation.ui.activity.PlayMusicActivity;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.presentation.utils.Constant;
import com.haidragon.musicapp.presentation.utils.UtilsSong;
import com.haidragon.musicapp.presentation.utils.HandleSong;
import com.haidragon.musicapp.presentation.utils.SharedPref;
import com.haidragon.musicapp.presentation.utils.SingletonApp;
import com.haidragon.musicapp.presentation.utils.UtilsImage;
import com.haidragon.musicapp.presentation.utils.build.SongBuilder;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0011\b\u0007\u0018\u0000 92\u00020\u00012\u00020\u0002:\u00019B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0003J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J!\u0010\"\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010#\u001a\u0004\u0018\u00010\u0017H\u0002\u00a2\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u0011H\u0016J\b\u0010&\u001a\u00020\u0011H\u0016J\u0016\u0010\'\u001a\u00020\u00112\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070)H\u0002J\u0016\u0010*\u001a\u00020\u00112\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070)H\u0002J\"\u0010+\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u0017H\u0017J\b\u0010.\u001a\u00020\u0011H\u0002J\b\u0010/\u001a\u00020\u0011H\u0002J\u0010\u00100\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J\b\u00101\u001a\u00020\u0011H\u0002J\b\u00102\u001a\u00020\u0011H\u0002J\u0017\u00103\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010\u0017H\u0002\u00a2\u0006\u0002\u00104J\u0010\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u0007H\u0002J\u0010\u00107\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u00108\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006:"}, d2 = {"Lcom/haidragon/musicapp/service/MusicService;", "Landroid/app/Service;", "Lcom/haidragon/musicapp/presentation/utils/HandleSong;", "()V", "coroutineScopeService", "Lkotlinx/coroutines/CoroutineScope;", "currentSong", "Lcom/haidragon/musicapp/domain/model/Song;", "sharedPreferences", "Landroid/content/SharedPreferences;", "singletonApp", "Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "getSingletonApp", "()Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "setSingletonApp", "(Lcom/haidragon/musicapp/presentation/utils/SingletonApp;)V", "closeMusic", "", "getPendingIntent", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "action", "", "nextMusic", "nextSong", "songNew", "notificationChannel", "notificationManagerCompat", "Landroidx/core/app/NotificationManagerCompat;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onClickAction", "position", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "onCreate", "onDestroy", "onNextSong", "listSongs", "", "onPreviousSong", "onStartCommand", "flags", "startId", "pauseMusic", "previousMusic", "previousSong", "reloadMusic", "resumeMusic", "seekBar", "(Ljava/lang/Integer;)V", "sendNotificationMusic", "song", "sendServiceToActivity", "startMusic", "Companion", "app_debug"})
public final class MusicService extends android.app.Service implements com.haidragon.musicapp.presentation.utils.HandleSong {
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.domain.model.Song currentSong;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope coroutineScopeService = null;
    private android.content.SharedPreferences sharedPreferences;
    @javax.inject.Inject
    public com.haidragon.musicapp.presentation.utils.SingletonApp singletonApp;
    @org.jetbrains.annotations.Nullable
    private static android.media.MediaPlayer mediaPlayer;
    @org.jetbrains.annotations.NotNull
    private static com.haidragon.musicapp.domain.model.Song song;
    @org.jetbrains.annotations.Nullable
    private static java.lang.Boolean isPlay;
    @org.jetbrains.annotations.Nullable
    private static java.lang.Boolean isService;
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.service.MusicService.Companion Companion = null;
    
    public MusicService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.presentation.utils.SingletonApp getSingletonApp() {
        return null;
    }
    
    public final void setSingletonApp(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.SingletonApp p0) {
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.Q)
    public int onStartCommand(@org.jetbrains.annotations.Nullable
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void onClickAction(java.lang.Integer action, java.lang.Integer position) {
    }
    
    private final void seekBar(java.lang.Integer position) {
    }
    
    private final void reloadMusic() {
    }
    
    private final void previousMusic() {
    }
    
    private final void pauseMusic() {
    }
    
    private final void nextMusic() {
    }
    
    private final void resumeMusic() {
    }
    
    private final void closeMusic() {
    }
    
    private final void startMusic(com.haidragon.musicapp.domain.model.Song song) {
    }
    
    private final void sendNotificationMusic(com.haidragon.musicapp.domain.model.Song song) {
    }
    
    private final android.app.PendingIntent getPendingIntent(android.content.Context context, int action) {
        return null;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    private final void notificationChannel(androidx.core.app.NotificationManagerCompat notificationManagerCompat) {
    }
    
    private final void sendServiceToActivity(int action) {
    }
    
    private final void onNextSong(java.util.List<com.haidragon.musicapp.domain.model.Song> listSongs) {
    }
    
    @java.lang.Override
    public void nextSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songNew) {
    }
    
    @java.lang.Override
    public void previousSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songNew) {
    }
    
    private final void onPreviousSong(java.util.List<com.haidragon.musicapp.domain.model.Song> listSongs) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\b\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/haidragon/musicapp/service/MusicService$Companion;", "", "()V", "isPlay", "", "()Ljava/lang/Boolean;", "setPlay", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "isService", "setService", "mediaPlayer", "Landroid/media/MediaPlayer;", "getMediaPlayer", "()Landroid/media/MediaPlayer;", "setMediaPlayer", "(Landroid/media/MediaPlayer;)V", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "getSong", "()Lcom/haidragon/musicapp/domain/model/Song;", "setSong", "(Lcom/haidragon/musicapp/domain/model/Song;)V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final android.media.MediaPlayer getMediaPlayer() {
            return null;
        }
        
        public final void setMediaPlayer(@org.jetbrains.annotations.Nullable
        android.media.MediaPlayer p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.haidragon.musicapp.domain.model.Song getSong() {
            return null;
        }
        
        public final void setSong(@org.jetbrains.annotations.NotNull
        com.haidragon.musicapp.domain.model.Song p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Boolean isPlay() {
            return null;
        }
        
        public final void setPlay(@org.jetbrains.annotations.Nullable
        java.lang.Boolean p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Boolean isService() {
            return null;
        }
        
        public final void setService(@org.jetbrains.annotations.Nullable
        java.lang.Boolean p0) {
        }
    }
}