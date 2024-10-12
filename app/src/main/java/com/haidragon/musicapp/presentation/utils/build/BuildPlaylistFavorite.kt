package com.haidragon.musicapp.presentation.utils.build

import com.haidragon.musicapp.R
import com.haidragon.musicapp.app.MyApp
import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.domain.model.Song

class BuildPlaylistFavorite : BuildPlaylist {
    private var listFilePathSong:List<Song> = listOf()
    private var name: String=""
    override fun buildName() {
        name = MyApp.instance.getString(R.string.favourite)
    }

    override fun resultPlaylist(): Playlist {
        return Playlist(name = name,
            listSongInPlaylist = listFilePathSong)
    }

    override fun setListFilePathSong(listFilePathSong: List<Song>) {
        this.listFilePathSong=listFilePathSong
    }
}