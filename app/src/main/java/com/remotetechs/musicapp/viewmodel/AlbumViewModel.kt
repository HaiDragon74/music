package com.remotetechs.musicapp.viewmodel

import android.content.Context
import android.content.LocusId
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.remotetechs.musicapp.model.Album
import com.remotetechs.musicapp.room.MusicDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AlbumViewModelFactory(private val musicDatabase: MusicDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(musicDatabase) as T
    }
}

class AlbumViewModel(private val musicDatabase: MusicDatabase) : ViewModel() {
    private val _livedataAlbum = MutableLiveData<List<Album>>()
    val liveDataAlbum: LiveData<List<Album>> = _livedataAlbum

    fun insertAlbum(context: Context, album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            val albumRoom = musicDatabase.dao().getAlbumByName(album.nameAlbum)
            if (albumRoom == null) {
                musicDatabase.dao().insertAlbum(album)
            } else
                viewModelScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, "Album already exists", Toast.LENGTH_SHORT).show()
                }
        }
    }


    fun realAlbum() {
        musicDatabase.dao().realAlbum().onEach { listAlbum ->
            _livedataAlbum.value = listAlbum
        }.launchIn(viewModelScope)
    }

    fun deleteAlbumById(album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            album.let { musicDatabase.dao().deleteAlbum(it) }
        }
    }
}