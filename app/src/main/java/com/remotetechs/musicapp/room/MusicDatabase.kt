package com.remotetechs.musicapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.remotetechs.musicapp.model.Album
import com.remotetechs.musicapp.model.Song

@Database(entities = [Song::class, Album::class], version = 1, exportSchema = false)
@TypeConverters(MusicTypeConverter::class)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun dao(): MusicDao

    companion object {
        private var INSTANCE: MusicDatabase? = null
        fun getCalculateDatabase(context: Context): MusicDatabase {
            var instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    MusicDatabase::class.java,
                    "table_song"
                ).build()
                instance = newInstance
                return newInstance
            }
        }
    }
}