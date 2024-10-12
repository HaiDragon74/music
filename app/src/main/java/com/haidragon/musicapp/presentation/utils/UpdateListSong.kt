package com.haidragon.musicapp.presentation.utils

import com.haidragon.musicapp.domain.model.Song

interface UpdateListSong {
    fun notifyDataChanged(song: Song)
}