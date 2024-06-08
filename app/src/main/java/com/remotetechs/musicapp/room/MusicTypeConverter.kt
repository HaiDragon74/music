package com.remotetechs.musicapp.room

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.Nullable
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.remotetechs.musicapp.model.Song
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
}