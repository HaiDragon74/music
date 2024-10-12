package com.haidragon.musicapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_song")
data class Song(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var artist: String,
    val coverArt: String,
    var filePath: String,
    var nameAlbum: String,
    var duration: Int,
)