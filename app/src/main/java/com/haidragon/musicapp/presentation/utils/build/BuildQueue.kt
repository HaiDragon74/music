package com.haidragon.musicapp.presentation.utils.build

import com.haidragon.musicapp.domain.model.Song

interface BuildQueue {
    fun setListSongQueue(listSongQueue: List<Song>)
    fun setSongPlay(songPlay: Song)
}