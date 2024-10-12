package com.haidragon.musicapp.presentation.utils.build;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H&\u00a8\u0006\u0011"}, d2 = {"Lcom/haidragon/musicapp/presentation/utils/build/BuildSong;", "", "setAlbum", "", "album", "", "setArtist", "artist", "setCoverArt", "coverArt", "setDuration", "duration", "", "setFilePath", "filePath", "setTitle", "title", "app_debug"})
public abstract interface BuildSong {
    
    public abstract void setTitle(@org.jetbrains.annotations.NotNull
    java.lang.String title);
    
    public abstract void setArtist(@org.jetbrains.annotations.NotNull
    java.lang.String artist);
    
    public abstract void setCoverArt(@org.jetbrains.annotations.NotNull
    java.lang.String coverArt);
    
    public abstract void setFilePath(@org.jetbrains.annotations.NotNull
    java.lang.String filePath);
    
    public abstract void setAlbum(@org.jetbrains.annotations.NotNull
    java.lang.String album);
    
    public abstract void setDuration(int duration);
}