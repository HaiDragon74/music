package com.haidragon.musicapp.presentation.utils

import com.haidragon.musicapp.presentation.utils.build.BuildSong

class SongDirector {
    fun construct(
        buildSong: BuildSong,
        title: String,
        album: String,
        convertArt: String,
        artist: String,
        filePath: String,
        duration: Int
    ){
        buildSong.setTitle(title)
        buildSong.setAlbum(album)
        buildSong.setCoverArt(convertArt)
        buildSong.setArtist(artist)
        buildSong.setFilePath(filePath)
        buildSong.setDuration(duration)
    }
}