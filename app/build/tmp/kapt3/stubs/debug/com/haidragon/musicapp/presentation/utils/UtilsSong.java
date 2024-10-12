package com.haidragon.musicapp.presentation.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.provider.MediaStore;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.haidragon.musicapp.domain.model.Song;
import com.haidragon.musicapp.presentation.utils.build.SongBuilder;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\rJ,\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00112\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/haidragon/musicapp/presentation/utils/UtilsSong;", "", "()V", "compareBitmap", "", "bitmapOne", "Landroid/graphics/Bitmap;", "bitmapTow", "getSongLocal", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/haidragon/musicapp/domain/model/Song;", "context", "Landroid/content/Context;", "observeOnce", "", "T", "Landroidx/lifecycle/LiveData;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "observer", "Landroidx/lifecycle/Observer;", "app_debug"})
public final class UtilsSong {
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.presentation.utils.UtilsSong INSTANCE = null;
    
    private UtilsSong() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.haidragon.musicapp.domain.model.Song>> getSongLocal(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    public final boolean compareBitmap(@org.jetbrains.annotations.Nullable
    android.graphics.Bitmap bitmapOne, @org.jetbrains.annotations.Nullable
    android.graphics.Bitmap bitmapTow) {
        return false;
    }
    
    public final <T extends java.lang.Object>void observeOnce(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.LiveData<T> $this$observeOnce, @org.jetbrains.annotations.NotNull
    androidx.lifecycle.LifecycleOwner lifecycleOwner, @org.jetbrains.annotations.NotNull
    androidx.lifecycle.Observer<T> observer) {
    }
}