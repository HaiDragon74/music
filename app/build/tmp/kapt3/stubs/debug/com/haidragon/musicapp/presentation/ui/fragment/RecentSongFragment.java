package com.haidragon.musicapp.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.databinding.FragmentRecentSongFragmetBinding;
import com.haidragon.musicapp.presentation.base.BaseFragment;
import com.remotetechs.musicapp.presentation.ui.adapter.SongInPlayListAdapter;
import com.haidragon.musicapp.presentation.utils.Constant;
import com.haidragon.musicapp.presentation.utils.OnBlackListener;
import com.haidragon.musicapp.presentation.utils.SharedPref;
import com.haidragon.musicapp.presentation.utils.SingletonApp;
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 .2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001.B\u0005\u00a2\u0006\u0002\u0010\u0003J$\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020$H\u0016J\u0010\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020$H\u0002J\b\u0010*\u001a\u00020$H\u0002J\b\u0010+\u001a\u00020$H\u0002J\b\u0010,\u001a\u00020$H\u0016J\b\u0010-\u001a\u00020$H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006/"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/RecentSongFragment;", "Lcom/haidragon/musicapp/presentation/base/BaseFragment;", "Lcom/haidragon/musicapp/databinding/FragmentRecentSongFragmetBinding;", "()V", "activity", "Landroidx/fragment/app/FragmentActivity;", "namePlayList", "", "onBlackListener", "Lcom/haidragon/musicapp/presentation/utils/OnBlackListener;", "recyclerSongRecent", "Landroidx/recyclerview/widget/RecyclerView;", "sharedPref", "Lcom/haidragon/musicapp/presentation/utils/SharedPref;", "singletonApp", "Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "getSingletonApp", "()Lcom/haidragon/musicapp/presentation/utils/SingletonApp;", "setSingletonApp", "(Lcom/haidragon/musicapp/presentation/utils/SingletonApp;)V", "songInPlayListAdapter", "Lcom/remotetechs/musicapp/presentation/ui/adapter/SongInPlayListAdapter;", "songViewModel", "Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "getSongViewModel", "()Lcom/haidragon/musicapp/presentation/viewmodel/SongViewModel;", "songViewModel$delegate", "Lkotlin/Lazy;", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "initUI", "", "observeViewModel", "onAttach", "context", "Landroid/content/Context;", "onBack", "removeCurrentFragment", "setUpRecycler", "setUpView", "setupViewModel", "Companion", "app_debug"})
public final class RecentSongFragment extends com.haidragon.musicapp.presentation.base.BaseFragment<com.haidragon.musicapp.databinding.FragmentRecentSongFragmetBinding> {
    private androidx.fragment.app.FragmentActivity activity;
    private com.haidragon.musicapp.presentation.utils.OnBlackListener onBlackListener;
    private androidx.recyclerview.widget.RecyclerView recyclerSongRecent;
    private com.haidragon.musicapp.presentation.utils.SharedPref sharedPref;
    private java.lang.String namePlayList;
    private com.remotetechs.musicapp.presentation.ui.adapter.SongInPlayListAdapter songInPlayListAdapter;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy songViewModel$delegate = null;
    @javax.inject.Inject
    public com.haidragon.musicapp.presentation.utils.SingletonApp singletonApp;
    @org.jetbrains.annotations.NotNull
    public static final com.haidragon.musicapp.presentation.ui.fragment.RecentSongFragment.Companion Companion = null;
    
    public RecentSongFragment() {
        super();
    }
    
    private final com.haidragon.musicapp.presentation.viewmodel.SongViewModel getSongViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.haidragon.musicapp.presentation.utils.SingletonApp getSingletonApp() {
        return null;
    }
    
    public final void setSingletonApp(@org.jetbrains.annotations.NotNull
    com.haidragon.musicapp.presentation.utils.SingletonApp p0) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.databinding.FragmentRecentSongFragmetBinding getViewBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onAttach(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @java.lang.Override
    public void initUI() {
    }
    
    @java.lang.Override
    public void setUpView() {
    }
    
    private final void setUpRecycler() {
    }
    
    @java.lang.Override
    public void observeViewModel() {
    }
    
    @java.lang.Override
    public void setupViewModel() {
    }
    
    private final void onBack() {
    }
    
    private final void removeCurrentFragment() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/fragment/RecentSongFragment$Companion;", "", "()V", "newInstance", "Lcom/haidragon/musicapp/presentation/ui/fragment/RecentSongFragment;", "namePlayList", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.haidragon.musicapp.presentation.ui.fragment.RecentSongFragment newInstance(@org.jetbrains.annotations.Nullable
        java.lang.String namePlayList) {
            return null;
        }
    }
}