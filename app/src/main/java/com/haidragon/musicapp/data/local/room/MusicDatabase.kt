package com.haidragon.musicapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.haidragon.musicapp.domain.model.Album
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.domain.model.SongQueue
import com.haidragon.musicapp.data.local.room.dao.MusicDao
import com.remotetechs.musicapp.data.local.room.typeconver.MusicTypeConverter

@Database(entities = [Song::class, Album::class, Playlist::class, SongQueue::class], version = 2, exportSchema = false)
@TypeConverters(MusicTypeConverter::class)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun dao(): MusicDao
    companion object {
        var INSTANCE: MusicDatabase? = null
    }
}