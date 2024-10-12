package com.haidragon.musicapp.presentation.utils.build;

import com.haidragon.musicapp.domain.model.Song;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H&\u00a8\u0006\t"}, d2 = {"Lcom/haidragon/musicapp/presentation/utils/build/BuildQueue;", "", "setListSongQueue", "", "listSongQueue", "", "Lcom/haidragon/musicapp/domain/model/Song;", "setSongPlay", "songPlay", "app_debug"})
public abstract interface BuildQueue {
    
    public abstract void setListSongQueue(@org.jetbrains.annotations.NotNull
    java.util.List<com.haidragon.musicapp.domain.model.Song> listSongQueue);
    
    public abstract void setSongPlay(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songPlay);
}