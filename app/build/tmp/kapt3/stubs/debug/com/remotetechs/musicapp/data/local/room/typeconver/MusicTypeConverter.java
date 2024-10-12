package com.remotetechs.musicapp.data.local.room.typeconver;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.haidragon.musicapp.domain.model.Song;
import java.io.ByteArrayOutputStream;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u0018\u0010\u000b\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fH\u0007J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u0004H\u0007J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\bH\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/remotetechs/musicapp/data/local/room/typeconver/MusicTypeConverter;", "", "()V", "fromBitmap", "", "bitmap", "Landroid/graphics/Bitmap;", "fromSong", "", "value", "Lcom/haidragon/musicapp/domain/model/Song;", "fromSongList", "", "toBitmap", "byteArray", "toSong", "toSongList", "app_debug"})
public final class MusicTypeConverter {
    
    public MusicTypeConverter() {
        super();
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final byte[] fromBitmap(@org.jetbrains.annotations.Nullable
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.Nullable
    public final android.graphics.Bitmap toBitmap(@org.jetbrains.annotations.NotNull
    byte[] byteArray) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromSongList(@org.jetbrains.annotations.Nullable
    java.util.List<com.haidragon.musicapp.domain.model.Song> value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.haidragon.musicapp.domain.model.Song> toSongList(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.Nullable
    public final com.haidragon.musicapp.domain.model.Song toSong(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromSong(@org.jetbrains.annotations.Nullable
    com.haidragon.musicapp.domain.model.Song value) {
        return null;
    }
}