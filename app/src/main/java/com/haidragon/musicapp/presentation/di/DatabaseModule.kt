package com.haidragon.musicapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.haidragon.musicapp.domain.repository.Repository
import com.haidragon.musicapp.data.repositoty.RepositoryImplement
import com.haidragon.musicapp.data.local.room.dao.MusicDao
import com.haidragon.musicapp.data.local.room.MusicDatabase
import com.haidragon.musicapp.presentation.utils.SingletonApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MusicDatabase {
            var instance = MusicDatabase.INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    appContext.applicationContext,
                    MusicDatabase::class.java,
                    "table_song"
                ).build()
                instance = newInstance
                return newInstance
            }
    }

    @Provides
    fun provideExampleDao(database: MusicDatabase): MusicDao {
        return database.dao()
    }

    @Provides
    fun provideRepository(musicDatabase: MusicDatabase): Repository {
        return RepositoryImplement(provideExampleDao(musicDatabase))
    }
    @Provides
    @Singleton
    fun providesSingleton(): SingletonApp {
        return SingletonApp()
    }
}