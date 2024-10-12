package com.haidragon.musicapp.presentation.utils

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Build
import android.provider.MediaStore
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.presentation.utils.build.SongBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object UtilsSong {
    fun getSongLocal(context: Context): Flow<List<Song>> = flow {
        val listSongs = mutableListOf<Song>()
        runCatching {
            val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val contentProvider = context.contentResolver
            val projection = arrayOf(
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
            )
            val selection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                "(${MediaStore.Audio.Media.IS_MUSIC} != 0" +
                        " AND ${MediaStore.Audio.Media.IS_RECORDING} = 0" +
                        " AND ${MediaStore.Audio.Media.DATA} LIKE '%.mp3')"
            } else {
                "(${MediaStore.Audio.Media.IS_MUSIC} != 0" +
                        " AND ${MediaStore.Audio.Media.IS_RECORDING} = 0" +
                        " AND ${MediaStore.Audio.Media.DATA} LIKE '%.mp3')"
            }

            val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"
            val cursor: Cursor? = contentProvider.query(uri, projection, selection, null, sortOrder)
            cursor.use { cs ->
                if (cs != null) {
                    while (cs.moveToNext()) {
                        val title: String =
                            cs.getString(cs.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                        val artist: String =
                            cs.getString(cs.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                        val filePath: String =
                            cs.getString(cs.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                        val duration: String =
                            cs.getString(cs.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                        val receiver = MediaMetadataRetriever()
                        receiver.setDataSource(filePath)
                        val byte = receiver.embeddedPicture
                        val bitmap = byte?.size?.let { BitmapFactory.decodeByteArray(byte, 0, it)}
                        val coverArt=if (bitmap!=null){
                            UtilsImage.bitmapToBase64(bitmap)
                        }else{
                            ""
                        }
                        val songBuilder = SongBuilder()
                        val songDirector = SongDirector()
                        songDirector.construct(
                            songBuilder,
                            title,
                            "",
                            coverArt,
                            artist,
                            filePath,
                            duration.toInt()
                        )
                        listSongs.add(songBuilder.getResult())
                    }
                }
            }
        }.onFailure {
            // Handle any exceptions that might occur
            it.printStackTrace()
        }
        emit(listSongs)
    }
    fun compareBitmap(bitmapOne: Bitmap?, bitmapTow: Bitmap?): Boolean {
        if (bitmapOne == null || bitmapTow == null) return false
        if (bitmapOne.width != bitmapTow.width || bitmapOne.height != bitmapTow.height) return false

        for (y in 0 until bitmapOne.height) {
            for (x in 0 until bitmapOne.width) {
                if (bitmapOne.getPixel(x, y) != bitmapTow.getPixel(x, y)) {
                    return false
                }
            }
        }
        return true
    }
    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(value: T) {
                observer.onChanged(value)
                removeObserver(this)
            }
        })
    }
}
