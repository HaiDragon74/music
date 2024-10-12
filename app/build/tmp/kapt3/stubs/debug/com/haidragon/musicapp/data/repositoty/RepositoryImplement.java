package com.haidragon.musicapp.data.repositoty;

import com.haidragon.musicapp.domain.model.Album;
import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.domain.model.SongQueue;
import com.haidragon.musicapp.data.local.room.dao.MusicDao;
import com.haidragon.musicapp.domain.repository.Repository;
import com.haidragon.musicapp.presentation.utils.InsertCallback;
import com.haidragon.musicapp.presentation.utils.UpdaterCallback;
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylistRecentlyPlayed;
import com.haidragon.musicapp.presentation.utils.build.BuildSongQueue;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00162\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J!\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J!\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\u001cH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J\u0019\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0013H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u0014\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00160(H\u0016J\u0014\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00160(H\u0016J,\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00160(2\u0006\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u0013H\u0016J\u001b\u0010.\u001a\u0004\u0018\u00010\u001a2\u0006\u0010%\u001a\u00020\u0013H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u0014\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00160(H\u0016J\u001e\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00160(2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0011\u00101\u001a\u00020!H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00102J/\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00132\f\u00105\u001a\b\u0012\u0004\u0012\u00020\f0\u00162\u0006\u00106\u001a\u000207H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108J\u0019\u00109\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010:R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006;"}, d2 = {"Lcom/haidragon/musicapp/data/repositoty/RepositoryImplement;", "Lcom/haidragon/musicapp/domain/repository/Repository;", "musicDao", "Lcom/haidragon/musicapp/data/local/room/dao/MusicDao;", "(Lcom/haidragon/musicapp/data/local/room/dao/MusicDao;)V", "deleteAlbum", "", "album", "Lcom/haidragon/musicapp/domain/model/Album;", "(Lcom/haidragon/musicapp/domain/model/Album;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSong", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "(Lcom/haidragon/musicapp/domain/model/Song;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSongById", "id", "", "getAlbumByName", "nameAlbum", "", "getSongById", "getSongByNameAlbum", "", "insertAlbum", "insertPlaylist", "playlist", "Lcom/haidragon/musicapp/domain/model/Playlist;", "insertCallback", "Lcom/haidragon/musicapp/presentation/utils/InsertCallback;", "(Lcom/haidragon/musicapp/domain/model/Playlist;Lcom/haidragon/musicapp/presentation/utils/InsertCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSong", "insertSongQueue", "songQueue", "Lcom/haidragon/musicapp/domain/model/SongQueue;", "(Lcom/haidragon/musicapp/domain/model/SongQueue;Lcom/haidragon/musicapp/presentation/utils/InsertCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPlaylistExists", "", "namePlaylist", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "realAlbum", "Lkotlinx/coroutines/flow/Flow;", "realPlaylist", "realPlaylistBottom", "nameRecentlyAdd", "nameRecentlyPlay", "nameMyTopTracks", "realPlaylistByName", "realSong", "realSongByAlbum", "realSongQueue", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePlaylistFilePathByName", "namPlaylist", "listSongInPlaylist", "updaterCallback", "Lcom/haidragon/musicapp/presentation/utils/UpdaterCallback;", "(Ljava/lang/String;Ljava/util/List;Lcom/haidragon/musicapp/presentation/utils/UpdaterCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSongQueue", "(Lcom/haidragon/musicapp/domain/model/SongQueue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RepositoryImplement implements com.haidragon.musicapp.domain.repository.Repository {
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.data.local.room.dao.MusicDao musicDao = null;
    
    public RepositoryImplement(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.data.local.room.dao.MusicDao musicDao) {
        super();
    }
    
    @java.lang.Override
    public void insertSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Song>> realSong() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object deleteSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public com.haidragon.musicapp.domain.model.Song getSongById(int id) {
        return null;
    }
    
    @java.lang.Override
    public void deleteSongById(int id) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.util.List<com.haidragon.musicapp.domain.model.Song> getSongByNameAlbum(@org.jetbrains.annotations.Nullable
    java.lang.String nameAlbum) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Song>> realSongByAlbum(@org.jetbrains.annotations.Nullable
    java.lang.String nameAlbum) {
        return null;
    }
    
    @java.lang.Override
    public void insertAlbum(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Album album) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Album>> realAlbum() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public com.haidragon.musicapp.domain.model.Album getAlbumByName(@org.jetbrains.annotations.Nullable
    java.lang.String nameAlbum) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object deleteAlbum(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Album album, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object insertPlaylist(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Playlist playlist, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.InsertCallback insertCallback, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> realPlaylistBottom(@org.jetbrains.annotations.NotNull
    java.lang.String nameRecentlyAdd, @org.jetbrains.annotations.NotNull
    java.lang.String nameRecentlyPlay, @org.jetbrains.annotations.NotNull
    java.lang.String nameMyTopTracks) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> realPlaylist() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object isPlaylistExists(@org.jetbrains.annotations.NotNull
    java.lang.String namePlaylist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object updatePlaylistFilePathByName(@org.jetbrains.annotations.NotNull
    java.lang.String namPlaylist, @org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongInPlaylist, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.UpdaterCallback updaterCallback, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object realPlaylistByName(@org.jetbrains.annotations.NotNull
    java.lang.String namePlaylist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.haidragon.musicapp.domain.model.Playlist> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object insertSongQueue(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.SongQueue songQueue, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.InsertCallback insertCallback, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object realSongQueue(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.haidragon.musicapp.domain.model.SongQueue> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object updateSongQueue(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.SongQueue songQueue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}