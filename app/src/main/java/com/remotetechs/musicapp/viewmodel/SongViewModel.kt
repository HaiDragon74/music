package com.remotetechs.musicapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.remotetechs.musicapp.room.MusicDatabase
import com.remotetechs.musicapp.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SongViewModelFactory(private val musicDatabase: MusicDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SongViewModel(musicDatabase) as T
    }
}

class SongViewModel(private val musicDatabase: MusicDatabase) : ViewModel() {
    private val _liveDataSong = MutableLiveData<List<Song>>()
    val liveDataSong: LiveData<List<Song>> = _liveDataSong
    private val _liveDataSongByAlbum = MutableLiveData<List<Song>>()
    val liveDataSongByAlbum: LiveData<List<Song>> = _liveDataSongByAlbum
    fun insertSongToFavourite(context: Context, song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            val listSongFavourite = musicDatabase.dao().getSongByNameAlbum("Favourite")
            val filePathExists = listSongFavourite.any { it.filePath == song.filePath }
            val toastMessage = if (!filePathExists) {
                musicDatabase.dao().insertSong(song)
                "Added song successfully in Favorites list"
            } else {
                "The song already exists in the Favorites list"
            }
            viewModelScope.launch(Dispatchers.Main) {
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun insertSongToAlbum(context: Context, song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            val listSongAlbum = musicDatabase.dao().getSongByNameAlbum(song.nameAlbum)
            val filePathExists = listSongAlbum.any { it.filePath == song.filePath }
            val toastMessage = if (!filePathExists) {
                musicDatabase.dao().insertSong(song)
                "Added song successfully in Album ${song.nameAlbum}"
            } else {
                "The song is included in the Album ${song.nameAlbum} "
            }
            viewModelScope.launch(Dispatchers.Main) {
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun realDataSong() {
        musicDatabase.dao().realSong().onEach { listSong ->
            _liveDataSong.value = listSong
        }.launchIn(viewModelScope)
    }

    fun realSongByAlbum(nameAlbum: String) {
        viewModelScope.launch(Dispatchers.IO) {
            musicDatabase.dao().realSongByAlbum(nameAlbum).onEach { listSong ->
                _liveDataSongByAlbum.value = listSong
            }.launchIn(viewModelScope)
        }
    }

    fun deleteSongByFilePath(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            song.id.let { musicDatabase.dao().deleteSongById(it) }
        }
    }
}