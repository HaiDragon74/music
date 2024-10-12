package com.haidragon.musicapp.presentation.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.domain.model.SongQueue;
import com.haidragon.musicapp.domain.repository.Repository;
import com.haidragon.musicapp.presentation.utils.Constant;
import com.haidragon.musicapp.presentation.utils.InsertCallback;
import com.haidragon.musicapp.presentation.utils.UpdaterCallback;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\bJ\u0014\u0010)\u001a\u00020\'2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0006\u0010+\u001a\u00020\'J\u000e\u0010,\u001a\u00020\'2\u0006\u0010-\u001a\u00020.J\u000e\u0010/\u001a\u00020\'2\u0006\u00100\u001a\u00020\rJ\u000e\u00101\u001a\u00020\'2\u0006\u00102\u001a\u00020\u0013J\u0016\u00103\u001a\u00020\'2\u0006\u00104\u001a\u0002052\u0006\u0010(\u001a\u00020\bJ\u0016\u00106\u001a\u00020\'2\u0006\u00104\u001a\u0002052\u0006\u0010(\u001a\u00020\bJ\u0006\u00107\u001a\u00020\'J\u001e\u00108\u001a\u00020\'2\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0010J\u000e\u0010<\u001a\u00020\'2\u0006\u0010=\u001a\u00020\u0010J\u000e\u0010>\u001a\u00020\'2\u0006\u0010?\u001a\u00020\u0010J\u0006\u0010@\u001a\u00020\'J\u001c\u0010A\u001a\u00020\'2\u0006\u0010B\u001a\u00020\u00102\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u000e\u0010D\u001a\u00020\'2\u0006\u00102\u001a\u00020\u0013R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006E"}, d2 = {"Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/haidragon/musicapp/domain/repository/Repository;", "(Lcom/haidragon/musicapp/domain/repository/Repository;)V", "_liveDataListSong", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/haidragon/musicapp/domain/model/Song;", "_liveDataSong", "_liveDataSongByAlbum", "_liveDataSongByIdRecently", "_mutableLiveDataListPlaylistBottom", "Lcom/haidragon/musicapp/domain/model/Playlist;", "_mutableLiveDataPlaylist", "_mutableLiveDataPlaylistStatus", "", "_mutableLiveDataPlaylists", "_mutableLiveDataSongQueue", "Lcom/haidragon/musicapp/domain/model/SongQueue;", "liveDatSongQueue", "Landroidx/lifecycle/LiveData;", "getLiveDatSongQueue", "()Landroidx/lifecycle/LiveData;", "liveDataListPlaylist", "getLiveDataListPlaylist", "liveDataListPlaylistBottom", "getLiveDataListPlaylistBottom", "liveDataListSong", "getLiveDataListSong", "liveDataPlaylist", "getLiveDataPlaylist", "liveDataSong", "getLiveDataSong", "liveDataSongByAlbum", "getLiveDataSongByAlbum", "liveDataSongByIdRecently", "getLiveDataSongByIdRecently", "deleteSongByFilePath", "", "song", "getListSong", "listSongs", "getPlaylists", "getSongByIdRecently", "id", "", "insertPlayList", "playlist", "insertSongQueue", "songQueue", "insertSongToAlbum", "context", "Landroid/content/Context;", "insertSongToFavourite", "realDataSong", "realPlaylistBottom", "nameRecentlyAdd", "nameRecentlyPlay", "nameMyTopTracks", "realPlaylistByName", "namePlaylist", "realSongByAlbum", "nameAlbum", "realSongQueue", "updatePlaylistFilePathByName", "namPlaylist", "listSongInPlaylist", "updateSongQueue", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class SongViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.domain.repository.Repository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.haidragon.musicapp.domain.model.Song>> _liveDataSong = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Song>> liveDataSong = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.haidragon.musicapp.domain.model.Song>> _liveDataSongByAlbum = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Song>> liveDataSongByAlbum = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.haidragon.musicapp.domain.model.Song>> _liveDataListSong = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Song>> liveDataListSong = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.haidragon.musicapp.domain.model.Song> _liveDataSongByIdRecently = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.haidragon.musicapp.domain.model.Song> liveDataSongByIdRecently = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> _mutableLiveDataPlaylists = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> liveDataListPlaylist = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> _mutableLiveDataListPlaylistBottom = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> liveDataListPlaylistBottom = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.haidragon.musicapp.domain.model.Playlist> _mutableLiveDataPlaylist = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.haidragon.musicapp.domain.model.Playlist> liveDataPlaylist = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.haidragon.musicapp.domain.model.SongQueue> _mutableLiveDataSongQueue = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.haidragon.musicapp.domain.model.SongQueue> liveDatSongQueue = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _mutableLiveDataPlaylistStatus = null;
    
    @javax.inject.Inject
    public SongViewModel(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.repository.Repository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Song>> getLiveDataSong() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Song>> getLiveDataSongByAlbum() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Song>> getLiveDataListSong() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.haidragon.musicapp.domain.model.Song> getLiveDataSongByIdRecently() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> getLiveDataListPlaylist() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> getLiveDataListPlaylistBottom() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.haidragon.musicapp.domain.model.Playlist> getLiveDataPlaylist() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.haidragon.musicapp.domain.model.SongQueue> getLiveDatSongQueue() {
        return null;
    }
    
    public final void insertSongToFavourite(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song) {
    }
    
    public final void insertSongToAlbum(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song) {
    }
    
    public final void realDataSong() {
    }
    
    public final void realSongByAlbum(@org.jetbrains.annotations.NotNull
    java.lang.String nameAlbum) {
    }
    
    public final void deleteSongByFilePath(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song) {
    }
    
    public final void getListSong(@org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongs) {
    }
    
    public final void getSongByIdRecently(int id) {
    }
    
    public final void insertPlayList(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Playlist playlist) {
    }
    
    public final void getPlaylists() {
    }
    
    public final void realPlaylistBottom(@org.jetbrains.annotations.NotNull
    java.lang.String nameRecentlyAdd, @org.jetbrains.annotations.NotNull
    java.lang.String nameRecentlyPlay, @org.jetbrains.annotations.NotNull
    java.lang.String nameMyTopTracks) {
    }
    
    public final void updatePlaylistFilePathByName(@org.jetbrains.annotations.NotNull
    java.lang.String namPlaylist, @org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongInPlaylist) {
    }
    
    public final void realPlaylistByName(@org.jetbrains.annotations.NotNull
    java.lang.String namePlaylist) {
    }
    
    public final void insertSongQueue(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.SongQueue songQueue) {
    }
    
    public final void realSongQueue() {
    }
    
    public final void updateSongQueue(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.SongQueue songQueue) {
    }
}