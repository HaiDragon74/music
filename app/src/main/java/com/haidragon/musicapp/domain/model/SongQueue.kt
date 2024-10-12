package com.haidragon.musicapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Song_queue")
data class SongQueue(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val listSongQueue: List<Song>,
    val songPlay: Song
)