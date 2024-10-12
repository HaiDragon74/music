package com.haidragon.musicapp.presentation.viewmodel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.haidragon.musicapp.domain.model.Album;
import com.haidragon.musicapp.domain.repository.Repository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u0016\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\bJ\u0006\u0010\u0013\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/haidragon/musicapp/presentation/viewmodel/AlbumViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/haidragon/musicapp/domain/repository/Repository;", "(Lcom/haidragon/musicapp/domain/repository/Repository;)V", "_livedataAlbum", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/haidragon/musicapp/domain/model/Album;", "liveDataAlbum", "Landroidx/lifecycle/LiveData;", "getLiveDataAlbum", "()Landroidx/lifecycle/LiveData;", "deleteAlbumById", "", "album", "insertAlbum", "context", "Landroid/content/Context;", "realAlbum", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class AlbumViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.haidragon.musicapp.domain.repository.Repository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.haidragon.musicapp.domain.model.Album>> _livedataAlbum = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Album>> liveDataAlbum = null;
    
    @javax.inject.Inject
    public AlbumViewModel(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.repository.Repository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.haidragon.musicapp.domain.model.Album>> getLiveDataAlbum() {
        return null;
    }
    
    public final void insertAlbum(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Album album) {
    }
    
    public final void realAlbum() {
    }
    
    public final void deleteAlbumById(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.domain.model.Album album) {
    }
}