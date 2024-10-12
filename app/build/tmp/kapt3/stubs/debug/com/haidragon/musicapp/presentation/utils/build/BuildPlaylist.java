package com.haidragon.musicapp.presentation.utils.build;

import com.haidragon.musicapp.domain.model.Playlist;
import com.haidragon.musicapp.domain.model.Song;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&\u00a8\u0006\n"}, d2 = {"Lcom/haidragon/musicapp/presentation/utils/build/BuildPlaylist;", "", "buildName", "", "resultPlaylist", "Lcom/haidragon/musicapp/domain/model/Playlist;", "setListFilePathSong", "listFilePathSong", "", "Lcom/haidragon/musicapp/domain/model/Song;", "app_debug"})
public abstract interface BuildPlaylist {
    
    public abstract void buildName();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.haidragon.musicapp.domain.model.Playlist resultPlaylist();
    
    public abstract void setListFilePathSong(@org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listFilePathSong);
}