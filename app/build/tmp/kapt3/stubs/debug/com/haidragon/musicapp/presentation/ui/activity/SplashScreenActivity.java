package com.haidragon.musicapp.presentation.ui.activity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import com.haidragon.musicapp.R;
import com.haidragon.musicapp.databinding.ActivityMainBinding;
import com.haidragon.musicapp.databinding.ActivitySplashScreenBinding;
import com.haidragon.musicapp.presentation.base.BaseActivity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/haidragon/musicapp/presentation/ui/activity/SplashScreenActivity;", "Lcom/haidragon/musicapp/presentation/base/BaseActivity;", "Lcom/haidragon/musicapp/databinding/ActivitySplashScreenBinding;", "()V", "handler", "Landroid/os/Handler;", "progressStatus", "", "runnable", "Ljava/lang/Runnable;", "getActivityViewBinding", "inflater", "Landroid/view/LayoutInflater;", "initUI", "", "setupView", "startMainActivity", "app_debug"})
@android.annotation.SuppressLint(value = {"CustomSplashScreen"})
public final class SplashScreenActivity extends com.haidragon.musicapp.presentation.base.BaseActivity<com.haidragon.musicapp.databinding.ActivitySplashScreenBinding> {
    private android.os.Handler handler;
    private java.lang.Runnable runnable;
    private int progressStatus = 0;
    
    public SplashScreenActivity() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.haidragon.musicapp.databinding.ActivitySplashScreenBinding getActivityViewBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater) {
        return null;
    }
    
    @java.lang.Override
    public void initUI() {
    }
    
    @java.lang.Override
    public void setupView() {
    }
    
    private final void startMainActivity() {
    }
}