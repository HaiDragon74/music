package com.haidragon.musicapp.presentation.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.haidragon.musicapp.R

object FragmentManager {
    fun switchFragment(fragment: Fragment, tag: String, singletonApp: SingletonApp, activity: FragmentActivity) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        if (singletonApp.activeFragment != null) {
            transaction.hide(singletonApp.activeFragment!!)
        }
        var fragmentToShow = activity.supportFragmentManager.findFragmentByTag(tag)
        if (fragmentToShow == null) {
            fragmentToShow = fragment
            transaction.add(R.id.fragment, fragmentToShow, tag)
        } else {
            transaction.show(fragmentToShow)
        }
        singletonApp.activeFragment = fragmentToShow
        transaction.commit()
    }
}