package com.remotetechs.musicapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.remotetechs.musicapp.model.Album
import com.remotetechs.musicapp.model.Song
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


}