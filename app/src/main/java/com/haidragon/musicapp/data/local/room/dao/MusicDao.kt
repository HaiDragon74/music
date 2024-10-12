package com.haidragon.musicapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.remotetechs.musicapp.data.local.room.typeconver.MusicTypeConverter
import com.haidragon.musicapp.domain.model.Album
import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.domain.model.SongQueue
import kotlinx.coroutines.flow.Flow

@Dao
@TypeConverters(MusicTypeConverter::class)
interface MusicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSong(song: Song)

    @Query("SELECT * FROM table_song")
    fun realSong(): Flow<List<Song>>

    @Delete
    suspend fun deleteSong(song: Song)

    @Query("DELETE FROM table_song WHERE id = :id")
    fun deleteSongById(id: Int)
    @Query("SELECT * FROM table_song WHERE id = :id")
    fun getSongById(id: Int): Song?

    @Query("SELECT * FROM table_song WHERE nameAlbum = :nameAlbum")
    fun getSongByNameAlbum(nameAlbum: String?): List<Song>

    @Query("SELECT * FROM table_song WHERE nameAlbum = :nameAlbum")
    fun realSongByAlbum(nameAlbum: String?): Flow<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: Album)

    @Query("SELECT * FROM table_album")
    fun realAlbum(): Flow<List<Album>>

    @Query("SELECT * FROM table_album WHERE nameAlbum = :nameAlbum")
    fun getAlbumByName(nameAlbum: String?): Album?

    @Delete
    suspend fun deleteAlbum(album: Album)

    //Play List
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: Playlist)
    @Query("SELECT * FROM table_playlist")
    fun realPlaylist(): Flow<List<Playlist>>
    @Query("SELECT * FROM table_playlist WHERE name NOT IN (:nameRecentlyAdd, :nameRecentlyPlay, :nameMyTopTracks)")
    fun realPlaylistBottom(nameRecentlyAdd: String, nameRecentlyPlay: String, nameMyTopTracks: String): Flow<List<Playlist>>
    @Query("SELECT EXISTS(SELECT 1 FROM table_playlist WHERE name = :namePlaylist)")
    suspend fun isPlaylistExists(namePlaylist: String): Boolean
    @Query("UPDATE table_playlist SET listSongInPlaylist = :listSongInPlaylist WHERE name = :namePlaylist")
    suspend fun updatePlaylistFilePathByName(namePlaylist: String, listSongInPlaylist: List<Song>)
    @Query("SELECT * FROM table_playlist  WHERE name = :namePlaylist")
    suspend fun realPlaylistByName(namePlaylist:String): Playlist?
    //SongQueue
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongQueue(songQueue: SongQueue)
    @Query("SELECT EXISTS(SELECT 1 FROM Song_queue WHERE songPlay = :songPlay)")
    suspend fun isSongQueueExists(songPlay: Song): Boolean
    @Query("SELECT * FROM Song_queue")
    suspend fun realSongQueue(): SongQueue
    @Update
    suspend fun updateSongQueue(songQueue: SongQueue)
    @Query("SELECT EXISTS(SELECT 1 FROM Song_queue WHERE id = :id)")
    suspend fun isSongQueueExists(id:Int): Boolean

}