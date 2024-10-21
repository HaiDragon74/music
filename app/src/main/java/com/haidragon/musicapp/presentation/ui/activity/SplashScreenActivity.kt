package com.haidragon.musicapp.presentation.ui.activity

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.haidragon.musicapp.R
import com.haidragon.musicapp.databinding.ActivityMainBinding
import com.haidragon.musicapp.databinding.ActivitySplashScreenBinding
import com.haidragon.musicapp.presentation.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var progressStatus = 0
    override fun getActivityViewBinding(inflater: LayoutInflater): ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun initUI() {
        super.initUI()
    }

    override fun setupView() {
        super.setupView()
        startMainActivity()
    }
    private fun startMainActivity() {
        //setting progress
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                if (progressStatus < 100) {
                    progressStatus++
                    binding.progressBarSplash.progress = progressStatus
                    handler.postDelayed(this, 40)
                } else {
                    val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
                    val anim = ActivityOptions.makeCustomAnimation(
                        this@SplashScreenActivity,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    ).toBundle()
                    startActivity(intent,anim)
                    finish()
                }
            }
        }
        handler.post(runnable)
    }
}