package com.haidragon.musicapp.presentation.utils

import com.haidragon.musicapp.domain.model.Song

interface HandleSong {
    fun nextSong(songNew: Song)
    fun previousSong(songNew: Song)
}