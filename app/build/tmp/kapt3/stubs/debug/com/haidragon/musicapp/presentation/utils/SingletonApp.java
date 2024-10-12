package com.haidragon.musicapp.presentation.utils;

import android.content.Context;
import androidx.fragment.app.Fragment;
import com.haidragon.musicapp.domain.model.Song;
import java.lang.ref.WeakReference;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u000bR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001b\"\u0004\b!\u0010\u001dR\u001c\u0010\"\u001a\u0004\u0018\u00010\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u0006*"}, d2 = {"Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "", "()V", "activeFragment", "Landroidx/fragment/app/Fragment;", "getActiveFragment", "()Landroidx/fragment/app/Fragment;", "setActiveFragment", "(Landroidx/fragment/app/Fragment;)V", "contextRef", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "getContextRef", "()Ljava/lang/ref/WeakReference;", "setContextRef", "(Ljava/lang/ref/WeakReference;)V", "isCheckRecently", "", "()Z", "setCheckRecently", "(Z)V", "isPlaying", "setPlaying", "listSongs", "", "Lcom/haidragon/musicapp/domain/model/Song;", "getListSongs", "()Ljava/util/List;", "setListSongs", "(Ljava/util/List;)V", "listSongsPlay", "", "getListSongsPlay", "setListSongsPlay", "songPlay", "getSongPlay", "()Lcom/haidragon/musicapp/domain/model/Song;", "setSongPlay", "(Lcom/haidragon/musicapp/domain/model/Song;)V", "initContext", "", "context", "app_debug"})
public final class SingletonApp {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.haidragon.musicapp.domain.model.Song> listSongs;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.haidragon.musicapp.domain.model.Song> listSongsPlay;
    @org.jetbrains.annotations.Nullable
    private com.haidragon.musicapp.domain.model.Song songPlay;
    @org.jetbrains.annotations.Nullable
    private androidx.fragment.app.Fragment activeFragment;
    private boolean isPlaying = false;
    private boolean isCheckRecently = true;
    @org.jetbrains.annotations.Nullable
    private java.lang.ref.WeakReference<android.content.Context> contextRef;
    
    public SingletonApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.haidragon.musicapp.domain.model.Song> getListSongs() {
        return null;
    }
    
    public final void setListSongs(@org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.haidragon.musicapp.domain.model.Song> getListSongsPlay() {
        return null;
    }
    
    public final void setListSongsPlay(@org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.haidragon.musicapp.domain.model.Song getSongPlay() {
        return null;
    }
    
    public final void setSongPlay(@org.jetbrains.annotations.Nullable
    com.haidragon.musicapp.domain.model.Song p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final androidx.fragment.app.Fragment getActiveFragment() {
        return null;
    }
    
    public final void setActiveFragment(@org.jetbrains.annotations.Nullable
    androidx.fragment.app.Fragment p0) {
    }
    
    public final boolean isPlaying() {
        return false;
    }
    
    public final void setPlaying(boolean p0) {
    }
    
    public final boolean isCheckRecently() {
        return false;
    }
    
    public final void setCheckRecently(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.ref.WeakReference<android.content.Context> getContextRef() {
        return null;
    }
    
    public final void setContextRef(@org.jetbrains.annotations.Nullable
    java.lang.ref.WeakReference<android.content.Context> p0) {
    }
    
    public final void initContext(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
}