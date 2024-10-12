package com.haidragon.musicapp.domain.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J-\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/haidragon/musicapp/domain/model/SongQueue;", "", "id", "", "listSongQueue", "", "Lcom/haidragon/musicapp/domain/model/Song;", "songPlay", "(ILjava/util/List;Lcom/haidragon/musicapp/domain/model/Song;)V", "getId", "()I", "getListSongQueue", "()Ljava/util/List;", "getSongPlay", "()Lcom/haidragon/musicapp/domain/model/Song;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
@androidx.room.Entity(tableName = "Song_queue")
public final class SongQueue {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final int id = 0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.haidragon.musicapp.domain.model.Song> listSongQueue = null;
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.domain.model.Song songPlay = null;
    
    public SongQueue(int id, @org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongQueue, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songPlay) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.haidragon.musicapp.domain.model.Song> getListSongQueue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.domain.model.Song getSongPlay() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.haidragon.musicapp.domain.model.Song> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.domain.model.Song component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.domain.model.SongQueue copy(int id, @org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongQueue, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songPlay) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}