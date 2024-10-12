package com.haidragon.musicapp.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haidragon.musicapp.domain.model.Album
import com.haidragon.musicapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _livedataAlbum = MutableLiveData<List<Album>>()
    val liveDataAlbum: LiveData<List<Album>> = _livedataAlbum

    fun insertAlbum(context: Context, album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            val albumRoom = repository.getAlbumByName(album.nameAlbum)
            if (albumRoom == null) {
                repository.insertAlbum(album)
            } else
                viewModelScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, "Album already exists", Toast.LENGTH_SHORT).show()
                }
        }
    }


    fun realAlbum() {
        repository.realAlbum().onEach { listAlbum ->
            _livedataAlbum.value = listAlbum
        }.launchIn(viewModelScope)
    }

    fun deleteAlbumById(album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            album.let { repository.deleteAlbum(it) }
        }
    }
}