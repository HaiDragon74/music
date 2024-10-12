package com.haidragon.musicapp.domain.repository
import com.haidragon.musicapp.domain.model.Album
import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.domain.model.SongQueue
import com.haidragon.musicapp.presentation.utils.InsertCallback
import com.haidragon.musicapp.presentation.utils.UpdaterCallback
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun insertSong(song: Song)
    fun realSong(): Flow<List<Song>>
    suspend fun deleteSong(song: Song)
    fun deleteSongById(id: Int)
    fun getSongByNameAlbum(nameAlbum: String?): List<Song>
    fun realSongByAlbum(nameAlbum: String?): Flow<List<Song>>
    fun insertAlbum(album: Album)
    fun realAlbum(): Flow<List<Album>>
    fun getAlbumByName(nameAlbum: String?): Album?
    fun getSongById(id: Int): Song?
    suspend fun deleteAlbum(album: Album)
    //playlist
    suspend  fun insertPlaylist(playlist: Playlist, insertCallback: InsertCallback)
    suspend  fun updatePlaylistFilePathByName(namPlaylist: String, listSongInPlaylist: List<Song>, updaterCallback: UpdaterCallback)
    fun  realPlaylist(): Flow<List<Playlist>>
    fun  realPlaylistBottom(nameRecentlyAdd: String, nameRecentlyPlay: String, nameMyTopTracks: String): Flow<List<Playlist>>
    suspend fun isPlaylistExists(namePlaylist: String): Boolean
    suspend fun realPlaylistByName(namePlaylist: String): Playlist?
    //songQueue
    suspend fun insertSongQueue(songQueue: SongQueue, insertCallback: InsertCallback)
    suspend fun realSongQueue(): SongQueue
    suspend fun updateSongQueue(songQueue: SongQueue)

}