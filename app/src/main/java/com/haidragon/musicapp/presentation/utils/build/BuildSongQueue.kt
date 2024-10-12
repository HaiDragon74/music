package com.haidragon.musicapp.presentation.utils.build

import com.haidragon.musicapp.R
import com.haidragon.musicapp.app.MyApp
import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.domain.model.SongQueue

class BuildSongQueue : BuildQueue {
    private var listSongQueue:List<Song> = listOf()
    private var song: Song =SongBuilder().getResult()

    override fun setListSongQueue(listSongQueue: List<Song>) {
        this.listSongQueue=listSongQueue
    }

    override fun setSongPlay(songPlay: Song) {
        this.song=songPlay
    }
    fun getResult(): SongQueue {
        return SongQueue(
            id = 1,
            listSongQueue = listSongQueue,
            songPlay = song
        )
    }
}