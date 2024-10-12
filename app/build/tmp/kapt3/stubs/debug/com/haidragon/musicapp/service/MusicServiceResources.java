package com.haidragon.musicapp.service;

import com.haidragon.musicapp.domain.model.Song;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&\u00a8\u0006\u000b"}, d2 = {"Lcom/haidragon/musicapp/service/MusicServiceResources;", "", "gonMenu", "", "goneActionPlay", "starService", "song", "Lcom/haidragon/musicapp/domain/model/Song;", "stopService", "visibleActionPlay", "visibleMenu", "app_debug"})
public abstract interface MusicServiceResources {
    
    public abstract void starService(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Song song);
    
    public abstract void stopService();
    
    public abstract void gonMenu();
    
    public abstract void goneActionPlay();
    
    public abstract void visibleActionPlay();
    
    public abstract void visibleMenu();
}