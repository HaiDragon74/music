package com.haidragon.musicapp.presentation.utils.build;

import com.haidragon.musicapp.R;
import com.haidragon.musicapp.app.MyApp;
import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.domain.model.SongQueue;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\n2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0005H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/haidragon/musicapp/presentation/utils/build/BuildSongQueue;", "Lcom/haidragon/musicapp/presentation/utils/build/BuildQueue;", "()V", "listSongQueue", "", "Lcom/haidragon/musicapp/domain/model/Song;", "song", "getResult", "Lcom/haidragon/musicapp/domain/model/SongQueue;", "setListSongQueue", "", "setSongPlay", "songPlay", "app_debug"})
public final class BuildSongQueue implements com.haidragon.musicapp.presentation.utils.build.BuildQueue {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.haidragon.musicapp.domain.model.Song> listSongQueue;
    @org.jetbrains.annotations.NotNull
    private com.haidragon.musicapp.domain.model.Song song;
    
    public BuildSongQueue() {
        super();
    }
    
    @java.lang.Override
    public void setListSongQueue(@org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongQueue) {
    }
    
    @java.lang.Override
    public void setSongPlay(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songPlay) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.domain.model.SongQueue getResult() {
        return null;
    }
}