package com.remotetechs.musicapp.model

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_song")
data class Song(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String? = null,
    var artist: String? = null,
    val coverArt: Bitmap? = null,
    var filePath: String? = null,
    var nameAlbum: String? = null,
    var duration: Int? = null
) {
    fun getSongLocal(context: Context): MutableList<Song> {
        val listSong = mutableListOf<Song>()
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
            val cs = contentProvider.query(uri, projection, selection, null, sortOrder)
            cs.use { cursor ->
                if (cursor != null) {
                    while (cs?.moveToNext() == true) {
                        val title: String =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                        val artist: String =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                        val filePath: String =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                        val duration: String =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                        val receiver = MediaMetadataRetriever()
                        receiver.setDataSource(filePath)
                        val byte = receiver.embeddedPicture
                        val bitmap = byte?.size?.let { BitmapFactory.decodeByteArray(byte, 0, it) }
                        val song = Song(
                            title = title,
                            artist = artist,
                            coverArt = bitmap,
                            filePath = filePath,
                            duration = duration.toInt()
                        )
                        listSong.add(song)
                    }
                }
            }
        }
        return listSong
    }
}