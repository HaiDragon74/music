package com.remotetechs.musicapp.data.local.room.typeconver

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.haidragon.musicapp.domain.model.Song
import java.io.ByteArrayOutputStream

class MusicTypeConverter {
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
    @TypeConverter
    fun fromSongList(value: List<Song>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toSongList(value: String): List<Song>? {
        val listType = object : TypeToken<List<Song>>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun toSong(value: String): Song? {
        val listType = object : TypeToken<Song>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromSong(value: Song?): String {
        return Gson().toJson(value)
    }

}