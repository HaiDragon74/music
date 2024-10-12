package com.haidragon.musicapp.presentation.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.fragment.app.FragmentActivity
import com.haidragon.musicapp.presentation.ui.fragment.HomeFragment

object UtilsAnimation {
    fun bottomToTop(view: View) {
        val slideUpAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f,
            Animation.RELATIVE_TO_SELF, 0f
        )
        slideUpAnimation.duration = 500
        slideUpAnimation.fillAfter = true
        view.startAnimation(slideUpAnimation)
    }
    fun topToBottom(view: View) {
        val slideDownAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f
        )
        slideDownAnimation.duration = 500
        slideDownAnimation.fillAfter = true
        view.startAnimation(slideDownAnimation)
    }
    //no animation view first
    fun showHomeFragment(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        val homeFragment = fragmentManager.findFragmentByTag("HomeFragment") as? HomeFragment
        when {
            homeFragment != null -> {
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.show(homeFragment)
                fragmentTransaction.commit()
            }
        }
    }
}