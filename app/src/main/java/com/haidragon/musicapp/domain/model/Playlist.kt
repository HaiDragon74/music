package com.haidragon.musicapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_playlist")
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    var listSongInPlaylist:List<Song>,
    var name: String
)