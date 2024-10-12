package com.haidragon.musicapp.presentation.utils.build;

import com.haidragon.musicapp.R;
import com.haidragon.musicapp.app.MyApp;
import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.domain.model.Song;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\t2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/haidragon/musicapp/presentation/utils/build/BuildPlaylistRecentlyPlayed;", "Lcom/haidragon/musicapp/presentation/utils/build/BuildPlaylist;", "()V", "listFilePathSong", "", "Lcom/haidragon/musicapp/domain/model/Song;", "name", "", "buildName", "", "resultPlaylist", "Lcom/haidragon/musicapp/domain/model/Playlist;", "setListFilePathSong", "app_debug"})
public final class BuildPlaylistRecentlyPlayed implements com.haidragon.musicapp.presentation.utils.build.BuildPlaylist {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.haidragon.musicapp.domain.model.Song> listFilePathSong;
    @org.jetbrains.annotations.NotNull
    private java.lang.String name = "";
    
    public BuildPlaylistRecentlyPlayed() {
        super();
    }
    
    @java.lang.Override
    public void buildName() {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.domain.model.Playlist resultPlaylist() {
        return null;
    }
    
    @java.lang.Override
    public void setListFilePathSong(@org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listFilePathSong) {
    }
}