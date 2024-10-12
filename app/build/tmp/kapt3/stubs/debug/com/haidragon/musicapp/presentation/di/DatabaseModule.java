package com.haidragon.musicapp.presentation.di;

import android.content.Context;
import androidx.room.Room;
import com.haidragon.musicapp.domain.repository.Repository;
import com.haidragon.musicapp.data.repositoty.RepositoryImplement;
import com.haidragon.musicapp.data.local.room.dao.MusicDao;
import com.haidragon.musicapp.data.local.room.MusicDatabase;
import com.haidragon.musicapp.presentation.utils.SingletonApp;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007\u00a8\u0006\u000f"}, d2 = {"Lcom/haidragon/musicapp/presentation/di/DatabaseModule;", "", "()V", "provideDatabase", "Lcom/haidragon/musicapp/data/local/room/MusicDatabase;", "appContext", "Landroid/content/Context;", "provideExampleDao", "Lcom/haidragon/musicapp/data/local/room/dao/MusicDao;", "database", "provideRepository", "Lcom/haidragon/musicapp/domain/repository/Repository;", "musicDatabase", "providesSingleton", "Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.presentation.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.data.local.room.MusicDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context appContext) {
        return null;
    }
    
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.data.local.room.dao.MusicDao provideExampleDao(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.data.local.room.MusicDatabase database) {
        return null;
    }
    
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.domain.repository.Repository provideRepository(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.data.local.room.MusicDatabase musicDatabase) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.presentation.utils.SingletonApp providesSingleton() {
        return null;
    }
}