package com.haidragon.musicapp.presentation.utils

import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylist
import com.haidragon.musicapp.presentation.utils.build.BuildQueue

class SongQueueDirector {
    fun construct(buildQueue: BuildQueue, listSongQueue:List<Song>, songPlay: Song){
        buildQueue.setListSongQueue(listSongQueue)
        buildQueue.setSongPlay(songPlay)
    }
}