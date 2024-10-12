package com.haidragon.musicapp.presentation.utils.build


interface BuildSong {
    fun setTitle(title: String)
    fun setArtist(artist: String)
    fun setCoverArt(coverArt: String)
    fun setFilePath(filePath: String)
    fun setAlbum(album: String)
    fun setDuration(duration: Int)
}