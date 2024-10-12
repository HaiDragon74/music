package com.haidragon.musicapp.presentation.utils;

import com.haidragon.musicapp.domain.model.Song;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lcom/haidragon/musicapp/presentation/utils/HandleSong;", "", "nextSong", "", "songNew", "Lcom/haidragon/musicapp/domain/model/Song;", "previousSong", "app_debug"})
public abstract interface HandleSong {
    
    public abstract void nextSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songNew);
    
    public abstract void previousSong(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song songNew);
}