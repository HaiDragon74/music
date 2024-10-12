package com.haidragon.musicapp.data.local.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;
import com.remotetechs.musicapp.data.local.room.typeconver.MusicTypeConverter;
import com.haidragon.musicapp.domain.model.Album;
import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.domain.model.SongQueue;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\'J\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\'J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\rH\'J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\'J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0019\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\'J\u0019\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0019\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\"\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\u0014\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00130&H\'J\u0014\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00130&H\'J,\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00130&2\u0006\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u0010H\'J\u001b\u0010,\u001a\u0004\u0018\u00010\u00172\u0006\u0010 \u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0014\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00130&H\'J\u001e\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00130&2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\'J\u0011\u0010/\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\'\u00101\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00102\f\u00102\u001a\b\u0012\u0004\u0012\u00020\t0\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00103J\u0019\u00104\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00065"}, d2 = {"Lcom/haidragon/musicapp/data/local/room/dao/MusicDao;", "", "deleteAlbum", "", "album", "Lcom/haidragon/musicapp/domain/model/Album;", "(Lcom/haidragon/musicapp/domain/model/Album;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSong", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "(Lcom/haidragon/musicapp/domain/model/Song;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSongById", "id", "", "getAlbumByName", "nameAlbum", "", "getSongById", "getSongByNameAlbum", "", "insertAlbum", "insertPlaylist", "playlist", "Lcom/haidragon/musicapp/domain/model/Playlist;", "(Lcom/haidragon/musicapp/domain/model/Playlist;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSong", "insertSongQueue", "songQueue", "Lcom/haidragon/musicapp/domain/model/SongQueue;", "(Lcom/haidragon/musicapp/domain/model/SongQueue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPlaylistExists", "", "namePlaylist", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSongQueueExists", "songPlay", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "realAlbum", "Lkotlinx/coroutines/flow/Flow;", "realPlaylist", "realPlaylistBottom", "nameRecentlyAdd", "nameRecentlyPlay", "nameMyTopTracks", "realPlaylistByName", "realSong", "realSongByAlbum", "realSongQueue", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePlaylistFilePathByName", "listSongInPlaylist", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSongQueue", "app_debug"})
@androidx.room.Dao
@androidx.room.TypeConverters(value = {com.remotetechs.musicapp.data.local.room.typeconver.MusicTypeConverter.class})
public abstract interface MusicDao {
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void insertSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song);
    
    @androidx.room.Query(value = "SELECT * FROM table_song")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Song>> realSong();
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM table_song WHERE id = :id")
    public abstract void deleteSongById(int id);
    
    @androidx.room.Query(value = "SELECT * FROM table_song WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract com.haidragon.musicapp.domain.model.Song getSongById(int id);
    
    @androidx.room.Query(value = "SELECT * FROM table_song WHERE nameAlbum = :nameAlbum")
    @org.jetbrains.annotations.NotNull
    public abstract java.util.List<com.haidragon.musicapp.domain.model.Song> getSongByNameAlbum(@org.jetbrains.annotations.Nullable
    java.lang.String nameAlbum);
    
    @androidx.room.Query(value = "SELECT * FROM table_song WHERE nameAlbum = :nameAlbum")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Song>> realSongByAlbum(@org.jetbrains.annotations.Nullable
    java.lang.String nameAlbum);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void insertAlbum(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Album album);
    
    @androidx.room.Query(value = "SELECT * FROM table_album")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Album>> realAlbum();
    
    @androidx.room.Query(value = "SELECT * FROM table_album WHERE nameAlbum = :nameAlbum")
    @org.jetbrains.annotations.Nullable
    public abstract com.haidragon.musicapp.domain.model.Album getAlbumByName(@org.jetbrains.annotations.Nullable
    java.lang.String nameAlbum);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAlbum(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Album album, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPlaylist(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Playlist playlist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM table_playlist")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> realPlaylist();
    
    @androidx.room.Query(value = "SELECT * FROM table_playlist WHERE name NOT IN (:nameRecentlyAdd, :nameRecentlyPlay, :nameMyTopTracks)")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Playlist>> realPlaylistBottom(@org.jetbrains.annotations.NotNull
    java.lang.String nameRecentlyAdd, @org.jetbrains.annotations.NotNull
    java.lang.String nameRecentlyPlay, @org.jetbrains.annotations.NotNull
    java.lang.String nameMyTopTracks);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM table_playlist WHERE name = :namePlaylist)")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object isPlaylistExists(@org.jetbrains.annotations.NotNull
    java.lang.String namePlaylist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @androidx.room.Query(value = "UPDATE table_playlist SET listSongInPlaylist = :listSongInPlaylist WHERE name = :namePlaylist")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updatePlaylistFilePathByName(@org.jetbrains.annotations.NotNull
    java.lang.String namePlaylist, @org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongInPlaylist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM table_playlist  WHERE name = :namePlaylist")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object realPlaylistByName(@org.jetbrains.annotations.NotNull
    java.lang.String namePlaylist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.haidragon.musicapp.domain.model.Playlist> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertSongQueue(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.SongQueue songQueue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM Song_queue WHERE songPlay = :songPlay)")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object isSongQueueExists(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songPlay, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM Song_queue")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object realSongQueue(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.haidragon.musicapp.domain.model.SongQueue> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateSongQueue(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.SongQueue songQueue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM Song_queue WHERE id = :id)")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object isSongQueueExists(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}