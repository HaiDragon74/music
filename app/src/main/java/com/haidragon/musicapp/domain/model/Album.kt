package com.haidragon.musicapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_album")
class Album(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nameAlbum: String? = null
) {
}