package com.haidragon.musicapp.presentation.utils.build

import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.domain.model.Song

interface BuildPlaylist {
    fun buildName()
    fun resultPlaylist(): Playlist
    fun setListFilePathSong(listFilePathSong:List<Song>)
}