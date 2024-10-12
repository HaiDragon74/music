package com.haidragon.musicapp.domain.repository;

import com.haidragon.musicapp.domain.model.Album;
import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.domain.model.SongQueue;
import com.haidragon.musicapp.presentation.utils.InsertCallback;
import com.haidragon.musicapp.presentation.utils.UpdaterCallback;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J!\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J!\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0019H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0019\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0010H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\u0014\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00130%H&J\u0014\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00130%H&J,\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00130%2\u0006\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u0010H&J\u001b\u0010+\u001a\u0004\u0018\u00010\u00172\u0006\u0010\"\u001a\u00020\u0010H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\u0014\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00130%H&J\u001e\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00130%2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\u0011\u0010.\u001a\u00020\u001eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J/\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u00102\f\u00102\u001a\b\u0012\u0004\u0012\u00020\t0\u00132\u0006\u00103\u001a\u000204H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105J\u0019\u00106\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00107\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00068"}, d2 = {"Lcom/haidragon/musicapp/domain/repository/Repository;", "", "deleteAlbum", "", "album", "Lcom/haidragon/musicapp/domain/model/Album;", "(Lcom/haidragon/musicapp/domain/model/Album;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSong", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "(Lcom/haidragon/musicapp/domain/model/Song;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSongById", "id", "", "getAlbumByName", "nameAlbum", "", "getSongById", "getSongByNameAlbum", "", "insertAlbum", "insertPlaylist", "playlist", "Lcom/haidragon/musicapp/domain/model/Playlist;", "insertCallback", "Lcom/haidragon/musicapp/presentation/utils/InsertCallback;", "(Lcom/haidragon/musicapp/domain/model/Playlist;Lcom/haidragon/musicapp/presentation/utils/InsertCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSong", "insertSongQueue", "songQueue", "Lcom/haidragon/musicapp/domain/model/SongQueue;", "(Lcom/haidragon/musicapp/domain/model/SongQueue;Lcom/haidragon/musicapp/presentation/utils/InsertCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPlaylistExists", "", "namePlaylist", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "realAlbum", "Lkotlinx/coroutines/flow/Flow;", "realPlaylist", "realPlaylistBottom", "nameRecentlyAdd", "nameRecentlyPlay", "nameMyTopTracks", "realPlaylistByName", "realSong", "realSongByAlbum", "realSongQueue", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePlaylistFilePathByName", "namPlaylist", "listSongInPlaylist", "updaterCallback", "Lcom/haidragon/musicapp/presentation/utils/UpdaterCallback;", "(Ljava/lang/String;Ljava/util/List;Lcom/haidragon/musicapp/presentation/utils/UpdaterCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSongQueue", "(Lcom/haidragon/musicapp/domain/model/SongQueue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface Repository {
    
    public abstract void insertSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Song>> realSong();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    public abstract void deleteSongById(int id);
    
    @org.jetbrains.annotations.NotNull
    public abstract java.util.List<com.haidragon.musicapp.domain.model.Song> getSongByNameAlbum(@org.jetbrains.annotations.Nullable
    java.lang.String nameAlbum);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Song>> realSongByAlbum(@org.jetbrains.annotations.Nullable
    java.lang.String nameAlbum);
    
    public abstract void insertAlbum(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Album album);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Album>> realAlbum();
    
    @org.jetbrains.annotations.Nullable
    public abstract com.haidragon.musicapp.domain.model.Album getAlbumByName(@org.jetbrains.annotations.Nullable
    java.lang.String nameAlbum);
    
    @org.jetbrains.annotations.Nullable
    public abstract com.haidragon.musicapp.domain.model.Song getSongById(int id);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAlbum(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Album album, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPlaylist(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Playlist playlist, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.InsertCallback insertCallback, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updatePlaylistFilePathByName(@org.jetbrains.annotations.NotNull
    java.lang.String namPlaylist, @org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongInPlaylist, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.UpdaterCallback updaterCallback, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> realPlaylist();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> realPlaylistBottom(@org.jetbrains.annotations.NotNull
    java.lang.String nameRecentlyAdd, @org.jetbrains.annotations.NotNull
    java.lang.String nameRecentlyPlay, @org.jetbrains.annotations.NotNull
    java.lang.String nameMyTopTracks);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object isPlaylistExists(@org.jetbrains.annotations.NotNull
    java.lang.String namePlaylist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object realPlaylistByName(@org.jetbrains.annotations.NotNull
    java.lang.String namePlaylist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.haidragon.musicapp.domain.model.Playlist> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertSongQueue(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.SongQueue songQueue, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.InsertCallback insertCallback, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object realSongQueue(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.haidragon.musicapp.domain.model.SongQueue> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateSongQueue(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.SongQueue songQueue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}