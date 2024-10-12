package com.haidragon.musicapp.presentation.utils.build

import com.haidragon.musicapp.domain.model.Song

class SongBuilder(): BuildSong {
    private var title: String=""
    private var artist: String=""
    private var coverArt: String=""
    private var filePath: String=""
    private var nameAlbum: String=""
    private var duration: Int=0
    override fun setTitle(title: String) {
        this.title =title
    }

    override fun setArtist(artist: String) {
        this.artist =artist
    }

    override fun setCoverArt(coverArt: String) {
        coverArt.let { this.coverArt =it }
    }

    override fun setFilePath(filePath: String) {
        this.filePath =filePath
    }

    override fun setAlbum(album: String) {
        album.let { this.nameAlbum =it }

    }

    override fun setDuration(duration: Int) {
        this.duration =duration
    }

    fun getResult(): Song {
        return Song(
            title = title,
            artist = artist,
            coverArt = coverArt,
            filePath = filePath,
            nameAlbum = nameAlbum,
            duration = duration
        )
    }
}