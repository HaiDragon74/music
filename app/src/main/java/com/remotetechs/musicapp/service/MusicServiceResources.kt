package com.remotetechs.musicapp.service

import com.remotetechs.musicapp.model.Song

interface MusicServiceResources {
    fun starService(song: Song)
    fun stopService()

    fun gonMenu()
    fun visibleMenu()


}