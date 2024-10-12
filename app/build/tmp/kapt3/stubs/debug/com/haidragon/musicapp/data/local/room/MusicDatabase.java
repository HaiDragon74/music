package com.haidragon.musicapp.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.haidragon.musicapp.domain.model.Album;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.domain.model.SongQueue;
import com.haidragon.musicapp.data.local.room.dao.MusicDao;
import com.remotetechs.musicapp.data.local.room.typeconver.MusicTypeConverter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/haidragon/musicapp/data/local/room/MusicDatabase;", "Landroidx/room/RoomDatabase;", "()V", "dao", "Lcom/haidragon/musicapp/data/local/room/dao/MusicDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.haidragon.musicapp.domain.model.Song.class, com.haidragon.musicapp.domain.model.Album.class, com.haidragon.musicapp.domain.model.Playlist.class, com.haidragon.musicapp.domain.model.SongQueue.class}, version = 2, exportSchema = false)
@androidx.room.TypeConverters(value = {com.remotetechs.musicapp.data.local.room.typeconver.MusicTypeConverter.class})
public abstract class MusicDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.Nullable
    private static com.haidragon.musicapp.data.local.room.MusicDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.data.local.room.MusicDatabase.Companion Companion = null;
    
    public MusicDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.haidragon.musicapp.data.local.room.dao.MusicDao dao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/haidragon/musicapp/data/local/room/MusicDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/haidragon/musicapp/data/local/room/MusicDatabase;", "getINSTANCE", "()Lcom/haidragon/musicapp/data/local/room/MusicDatabase;", "setINSTANCE", "(Lcom/haidragon/musicapp/data/local/room/MusicDatabase;)V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.haidragon.musicapp.data.local.room.MusicDatabase getINSTANCE() {
            return null;
        }
        
        public final void setINSTANCE(@org.jetbrains.annotations.Nullable
        com.haidragon.musicapp.data.local.room.MusicDatabase p0) {
        }
    }
}