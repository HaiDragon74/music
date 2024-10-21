package com.haidragon.musicapp.presentation.utils

import android.content.Context
import com.google.gson.Gson
import com.haidragon.musicapp.app.MyApp

class SharedPref {
    private val sharedPreferences by lazy {
        MyApp.instance.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
    fun savePositionRecentSong(title: String?) {
        val currentPositions = getPositionRecentSongs().toMutableList()
        currentPositions.add(title)
        val json = Gson().toJson(currentPositions)
        sharedPreferences?.edit()?.putString(Constant.KEY_NAME_RECENT_SONG, json)?.apply()
    }
    fun getPositionRecentSongs(): List<String?> {
        val json = sharedPreferences?.getString(Constant.KEY_NAME_RECENT_SONG, null)
        return if (json != null) {
            Gson().fromJson(json, Array<String>::class.java).toList() // Convert JSON to List
        } else {
            emptyList() // Return an empty list if no data is found
        }
    }
}