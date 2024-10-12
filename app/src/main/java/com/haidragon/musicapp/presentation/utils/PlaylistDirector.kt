package com.haidragon.musicapp.presentation.utils

import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylist

class PlaylistDirector {
    fun construct(buildPlaylist: BuildPlaylist, listFilePathSong:List<Song>){
        buildPlaylist.buildName()
        buildPlaylist.setListFilePathSong(listFilePathSong)
    }
}