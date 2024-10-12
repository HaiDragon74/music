package com.haidragon.musicapp.presentation.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.domain.model.SongQueue
import com.haidragon.musicapp.domain.repository.Repository
import com.haidragon.musicapp.presentation.utils.Constant
import com.haidragon.musicapp.presentation.utils.InsertCallback
import com.haidragon.musicapp.presentation.utils.UpdaterCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _liveDataSong = MutableLiveData<List<Song>>()
    val liveDataSong: LiveData<List<Song>> = _liveDataSong
    private val _liveDataSongByAlbum = MutableLiveData<List<Song>>()
    val liveDataSongByAlbum: LiveData<List<Song>> = _liveDataSongByAlbum
    private val _liveDataListSong = MutableLiveData<List<Song>>()
    val liveDataListSong: LiveData<List<Song>> = _liveDataListSong
    private val _liveDataSongByIdRecently = MutableLiveData<Song>()
    val liveDataSongByIdRecently: LiveData<Song> = _liveDataSongByIdRecently
    private val _mutableLiveDataPlaylists = MutableLiveData<List<Playlist>>()
    val liveDataListPlaylist: LiveData<List<Playlist>> = _mutableLiveDataPlaylists
    private val _mutableLiveDataListPlaylistBottom = MutableLiveData<List<Playlist>>()
    val liveDataListPlaylistBottom: LiveData<List<Playlist>> = _mutableLiveDataListPlaylistBottom
    private val _mutableLiveDataPlaylist = MutableLiveData<Playlist>()
    val liveDataPlaylist: LiveData<Playlist> = _mutableLiveDataPlaylist
    private val _mutableLiveDataSongQueue = MutableLiveData<SongQueue>()
    val liveDatSongQueue: LiveData<SongQueue> = _mutableLiveDataSongQueue
    private val _mutableLiveDataPlaylistStatus = MutableLiveData<String>()
    fun insertSongToFavourite(context: Context, song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            val listSongFavourite = repository.getSongByNameAlbum("Favourite")
            val filePathExists = listSongFavourite.any { it.filePath == song.filePath }
            val toastMessage = if (!filePathExists) {
                repository.insertSong(song)
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
            val listSongAlbum = repository.getSongByNameAlbum(song.nameAlbum)
            val filePathExists = listSongAlbum.any { it.filePath == song.filePath }
            val toastMessage = if (!filePathExists) {
                repository.insertSong(song)
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
        repository.realSong().onEach { listSong ->
            _liveDataSong.value = listSong
        }.launchIn(viewModelScope)
    }

    fun realSongByAlbum(nameAlbum: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.realSongByAlbum(nameAlbum).onEach { listSong ->
                _liveDataSongByAlbum.value = listSong
            }.launchIn(viewModelScope)
        }
    }

    fun deleteSongByFilePath(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            song.id.let { repository.deleteSongById(it) }
        }
    }
    fun getListSong(listSongs: List<Song>){
        _liveDataListSong.value=listSongs
    }
    fun getSongByIdRecently(id: Int){
        _liveDataSongByIdRecently.value=repository.getSongById(id)
    }
    //playlist
    fun insertPlayList(playlist: Playlist){
        viewModelScope.launch {
            repository.insertPlaylist(playlist,object : InsertCallback {
                override fun onSuccess(result: Unit?) {
                    //insert success playlist default
                }
                override fun onFailure(errorMessage: String) {
                    //insert onFailure playlist default
                }
            })
        }
    }
    fun getPlaylists() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.realPlaylist().collect { listPlaylist ->
                withContext(Dispatchers.Main) {
                    _mutableLiveDataPlaylists.value = listPlaylist
                }
            }
        }
    }
    fun realPlaylistBottom(nameRecentlyAdd: String, nameRecentlyPlay: String, nameMyTopTracks: String){
        viewModelScope.launch {
            repository.realPlaylistBottom(nameRecentlyAdd, nameRecentlyPlay, nameMyTopTracks).collect{listPlaylist->
                withContext(Dispatchers.Main){
                    _mutableLiveDataListPlaylistBottom.value=listPlaylist
                }
            }
        }
    }
    fun updatePlaylistFilePathByName(namPlaylist: String,listSongInPlaylist:List<Song>){
        viewModelScope.launch {
            repository.updatePlaylistFilePathByName(namPlaylist,listSongInPlaylist,object :
                UpdaterCallback {
                override fun onSuccess(result: Unit?) {
                    //success update playlist
                }

                override fun onFailure(errorMessage: String) {
                    //failure update playlist
                }
            })
        }
    }
    fun realPlaylistByName(namePlaylist: String){
        viewModelScope.launch {
            _mutableLiveDataPlaylist.value= repository.realPlaylistByName(namePlaylist)
        }
    }
    fun insertSongQueue(songQueue: SongQueue){
        viewModelScope.launch {
            repository.insertSongQueue(songQueue,object :InsertCallback{
                override fun onSuccess(result: Unit?) {
                    Log.d("${Constant.TAG}insertSongQueue","success insert songQueue")
                }

                override fun onFailure(errorMessage: String) {
                    Log.d("${Constant.TAG}insertSongQueue","failure insert songQueue")
                }
            })
        }
    }
    fun realSongQueue(){
        viewModelScope.launch {
            _mutableLiveDataSongQueue.value=repository.realSongQueue()
        }
    }
    fun updateSongQueue(songQueue: SongQueue){
        viewModelScope.launch {
            repository.updateSongQueue(songQueue)
        }
    }

}