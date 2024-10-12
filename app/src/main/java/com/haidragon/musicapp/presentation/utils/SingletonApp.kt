package com.haidragon.musicapp.presentation.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.haidragon.musicapp.domain.model.Song
import java.lang.ref.WeakReference

class SingletonApp(){
    var listSongs:List<Song> = listOf()
    var listSongsPlay:MutableList<Song> = mutableListOf()
    var songPlay: Song?=null
    var activeFragment: Fragment? = null
    var isPlaying:Boolean=false
    var isCheckRecently:Boolean=true
    var contextRef: WeakReference<Context>? = null
    fun initContext(context: Context) {
        contextRef = WeakReference(context.applicationContext)
    }
}