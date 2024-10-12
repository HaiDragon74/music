package com.haidragon.musicapp.service

import com.haidragon.musicapp.domain.model.Song

interface MusicServiceResources {
    fun starService(song: Song)
    fun stopService()
    fun gonMenu()
    fun goneActionPlay()
    fun visibleActionPlay()
    fun visibleMenu()
}