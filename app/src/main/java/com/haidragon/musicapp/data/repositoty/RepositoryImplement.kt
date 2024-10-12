package com.haidragon.musicapp.data.repositoty

import com.haidragon.musicapp.domain.model.Album
import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.domain.model.SongQueue
import com.haidragon.musicapp.data.local.room.dao.MusicDao
import com.haidragon.musicapp.domain.repository.Repository
import com.haidragon.musicapp.presentation.utils.InsertCallback
import com.haidragon.musicapp.presentation.utils.UpdaterCallback
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylistRecentlyPlayed
import com.haidragon.musicapp.presentation.utils.build.BuildSongQueue
import kotlinx.coroutines.flow.Flow

class RepositoryImplement(private val musicDao: MusicDao) : Repository {
    override fun insertSong(song: Song) {
        return musicDao.insertSong(song)
    }

    override fun realSong(): Flow<List<Song>> {
        return musicDao.realSong()
    }

    override suspend fun deleteSong(song: Song) {
        return musicDao.deleteSong(song)
    }

    override fun getSongById(id: Int): Song? {
        return musicDao.getSongById(id)
    }

    override fun deleteSongById(id: Int) {
        return musicDao.deleteSongById(id)
    }

    override fun getSongByNameAlbum(nameAlbum: String?): List<Song> {
        return musicDao.getSongByNameAlbum(nameAlbum)
    }

    override fun realSongByAlbum(nameAlbum: String?): Flow<List<Song>> {
        return musicDao.realSongByAlbum(nameAlbum)
    }

    override fun insertAlbum(album: Album) {
        return musicDao.insertAlbum(album)
    }

    override fun realAlbum(): Flow<List<Album>> {
        return musicDao.realAlbum()
    }

    override fun getAlbumByName(nameAlbum: String?): Album? {
        return musicDao.getAlbumByName(nameAlbum)
    }

    override suspend fun deleteAlbum(album: Album) {
        return musicDao.deleteAlbum(album)
    }

    override suspend fun insertPlaylist(playlist: Playlist, insertCallback: InsertCallback) {
        val exists = playlist.name.let { it?.let { it1 -> musicDao.isPlaylistExists(it1) } }?:false
        return if (!exists){
            insertCallback.onSuccess(playlist.let { musicDao.insertPlaylist(it) })
        }else{
            insertCallback.onFailure("exists is Playlist")
        }
    }

    override fun realPlaylistBottom(nameRecentlyAdd: String, nameRecentlyPlay: String, nameMyTopTracks: String): Flow<List<Playlist>> {
        return musicDao.realPlaylistBottom(nameRecentlyAdd, nameRecentlyPlay, nameMyTopTracks)
    }

    override fun realPlaylist(): Flow<List<Playlist>> {
        return musicDao.realPlaylist()
    }
    override suspend fun isPlaylistExists(namePlaylist: String): Boolean {
        return musicDao.isPlaylistExists(namePlaylist)
    }

    override suspend fun updatePlaylistFilePathByName(namPlaylist: String, listSongInPlaylist: List<Song>, updaterCallback: UpdaterCallback) {
        val listSongUpdate = musicDao.realPlaylistByName(namPlaylist)?.listSongInPlaylist ?: listOf()
        val newSongs = listSongInPlaylist.filter { newSong ->
            listSongUpdate.none { it.filePath == newSong.filePath }
        }
        if (newSongs.isNotEmpty()) {
            val updatedList = listSongUpdate + newSongs
            musicDao.updatePlaylistFilePathByName(namPlaylist, updatedList)
            updaterCallback.onSuccess( musicDao.updatePlaylistFilePathByName(namPlaylist, updatedList))
        } else {
            updaterCallback.onFailure("Error update playlist")
        }
    }

    override suspend fun realPlaylistByName(namePlaylist: String): Playlist? {
        val exists = musicDao.isPlaylistExists(namePlaylist)
        return if (!exists){
            return BuildPlaylistRecentlyPlayed().resultPlaylist()
        }else{
            return musicDao.realPlaylistByName(namePlaylist)
            //error playlist not exists
        }
    }

    //SongQueue
    override suspend fun insertSongQueue(songQueue: SongQueue, insertCallback: InsertCallback) {
        val exists =musicDao.isSongQueueExists(songQueue.songPlay)
        return if (!exists){
            insertCallback.onSuccess(musicDao.insertSongQueue(songQueue))
        }else{
            insertCallback.onFailure("exists is SongQueue")
        }
    }

    override suspend fun realSongQueue(): SongQueue {
        val exists = musicDao.isSongQueueExists(1)
        return if (!exists){
            BuildSongQueue().getResult()
        }else{
            musicDao.realSongQueue()
        }
    }

    override suspend fun updateSongQueue(songQueue: SongQueue) {
        return musicDao.updateSongQueue(songQueue)
    }
}