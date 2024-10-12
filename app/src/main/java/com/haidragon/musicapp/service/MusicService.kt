package com.haidragon.musicapp.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.haidragon.musicapp.R
import com.haidragon.musicapp.presentation.ui.activity.PlayMusicActivity
import com.haidragon.musicapp.domain.model.Song
import com.haidragon.musicapp.presentation.utils.Constant
import com.haidragon.musicapp.presentation.utils.Constant.CHANNEL_ID
import com.haidragon.musicapp.presentation.utils.Constant.CHANNEL_NAME
import com.haidragon.musicapp.presentation.utils.Constant.CLOSE
import com.haidragon.musicapp.presentation.utils.Constant.KEY_ACTION_TO_ACTIVITY
import com.haidragon.musicapp.presentation.utils.Constant.KEY_DATA_SONG
import com.haidragon.musicapp.presentation.utils.Constant.KEY_IS_PLAY_TO_ACTIVITY
import com.haidragon.musicapp.presentation.utils.Constant.KEY_SEND_ACTIVITY
import com.haidragon.musicapp.presentation.utils.Constant.KEY_SEND_RECEIVER
import com.haidragon.musicapp.presentation.utils.Constant.KEY_SEND_SERVICE
import com.haidragon.musicapp.presentation.utils.Constant.KEY_SONG_TO_ACTIVITY
import com.haidragon.musicapp.presentation.utils.Constant.NEXT
import com.haidragon.musicapp.presentation.utils.Constant.PAUSE
import com.haidragon.musicapp.presentation.utils.Constant.PREVIOUS
import com.haidragon.musicapp.presentation.utils.Constant.RELOAD
import com.haidragon.musicapp.presentation.utils.Constant.RESUME
import com.haidragon.musicapp.presentation.utils.Constant.SEEKBAR
import com.haidragon.musicapp.presentation.utils.Constant.START
import com.haidragon.musicapp.presentation.utils.UtilsSong
import com.haidragon.musicapp.presentation.utils.HandleSong
import com.haidragon.musicapp.presentation.utils.SharedPref
import com.haidragon.musicapp.presentation.utils.SingletonApp
import com.haidragon.musicapp.presentation.utils.UtilsImage
import com.haidragon.musicapp.presentation.utils.build.SongBuilder
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class MusicService : Service(), HandleSong {
    private var currentSong: Song? = null
    private val coroutineScopeService = CoroutineScope(IO)
    private lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var singletonApp: SingletonApp

    companion object {
        var mediaPlayer: MediaPlayer? = null
        var song = SongBuilder().getResult()
        var isPlay: Boolean? = null
        var isService: Boolean? = null
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences=getSharedPreferences(Constant.KEY_SHARED_PREFERENCES,Context.MODE_PRIVATE)
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val sonJson = intent?.getStringExtra(KEY_DATA_SONG)
        val dataSong = Gson().fromJson(sonJson, Song::class.java)
        if (dataSong != null) {
            song = dataSong
            singletonApp.listSongsPlay.add(song)
            if (song.filePath != currentSong?.filePath) {
                startMusic(song)
                currentSong = song
            }
            sendNotificationMusic(song)
        }
        val action = intent?.getIntExtra(KEY_SEND_SERVICE, 0)
        val position = intent?.getIntExtra("position", 0)
        onClickAction(action, position)
        return START_NOT_STICKY
    }


    private fun onClickAction(action: Int?, position: Int?) {
        when (action) {
            1 -> previousMusic()
            2 -> pauseMusic()
            3 -> nextMusic()
            4 -> resumeMusic()
            5 -> closeMusic()
            7 -> reloadMusic()
            8 -> seekBar(position)

        }
    }

    private fun seekBar(position: Int?) {
        if (position != null) {
            mediaPlayer?.seekTo(position)
        }

        sendServiceToActivity(SEEKBAR)
    }

    private fun reloadMusic() {
        sendServiceToActivity(RELOAD)
    }

    private fun previousMusic() {
        mediaPlayer?.release()
        coroutineScopeService.launch(IO) {
            onPreviousSong(singletonApp.listSongsPlay)
            mediaPlayer = MediaPlayer.create(applicationContext, song.filePath.toUri())
            mediaPlayer?.start()
            isPlay = true
            startMusic(song)
            sendNotificationMusic(song)
            sendServiceToActivity(PAUSE)
        }
    }

    private fun pauseMusic() {
        if (isPlay == true) {
            mediaPlayer?.pause()
            isPlay = false
        }
        sendNotificationMusic(song)
        sendServiceToActivity(PAUSE)
    }


    private fun nextMusic() {
        mediaPlayer?.release()
        coroutineScopeService.launch(IO) {
            onNextSong(singletonApp.listSongsPlay)
            mediaPlayer = MediaPlayer.create(applicationContext, song.filePath.toUri())
            mediaPlayer?.start()
            isPlay = true
            startMusic(song)
            sendNotificationMusic(song)
            sendServiceToActivity(NEXT)
        }
    }

    private fun resumeMusic() {
        if (isPlay == false) {
            mediaPlayer?.start()
            isPlay = true
        }
        sendNotificationMusic(song)
        sendServiceToActivity(RESUME)
    }

    private fun closeMusic() {
        stopSelf()
        mediaPlayer?.release()
        isPlay = false
        isService = false
        mediaPlayer =null
        sendServiceToActivity(CLOSE)
    }

    private fun startMusic(song: Song) {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.release()
            mediaPlayer = null
            isPlay = false
        }
        SharedPref().savePositionRecentSong(song.title)
        mediaPlayer = MediaPlayer.create(applicationContext, song.filePath.toUri())
        mediaPlayer?.start()
        isPlay = true
        isService = true
        mediaPlayer?.setOnCompletionListener {
            nextMusic()
        }
        val songJson=Gson().toJson(song)
        sharedPreferences.edit().putString(Constant.KEY_SAVE_SONG_PLAY_LAST,songJson).apply()
        sendServiceToActivity(START)
    }

    private fun sendNotificationMusic(song: Song) {
        val intent = Intent(this, PlayMusicActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_music)
            .setSubText("Music")
            .setContentTitle(song.title)
            .setContentText(song.artist)
            .setContentIntent(pendingIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView()
            )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel(notificationManagerCompat)
        }
        if ((UtilsImage.base64ToBitmap(song.coverArt))?.isPremultiplied == true) {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_img_avt)
            notificationBuilder.setLargeIcon(bitmap)
        } else
            notificationBuilder.setLargeIcon(UtilsImage.base64ToBitmap(song.coverArt))
        if (isPlay == true) {
            notificationBuilder
                .addAction(R.drawable.ic_previous, "previous", getPendingIntent(this, PREVIOUS))
                .addAction(R.drawable.ic_pause, "pause", getPendingIntent(this, PAUSE))
                .addAction(R.drawable.ic_next, "next", getPendingIntent(this, NEXT))
                .addAction(R.drawable.ic_close, "close", getPendingIntent(this, CLOSE))
        } else {
            notificationBuilder
                .addAction(R.drawable.ic_previous, "previous", getPendingIntent(this, PREVIOUS))
                .addAction(R.drawable.ic_resume, "resume", getPendingIntent(this, RESUME))
                .addAction(R.drawable.ic_next, "next", getPendingIntent(this, NEXT))
                .addAction(R.drawable.ic_close, "close", getPendingIntent(this, CLOSE))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK
                ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForeground(
                        1,
                        notificationBuilder.build(),
                        FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
                    )
                }
            }

        } else {
            startForeground(1, notificationBuilder.build())
        }
    }

    private fun getPendingIntent(context: Context, action: Int): PendingIntent? {
        val intent = Intent(this, MusicBroadcastReceiver::class.java)
        intent.putExtra(KEY_SEND_RECEIVER, action)
        return PendingIntent.getBroadcast(
            context.applicationContext,
            action,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notificationChannel(notificationManagerCompat: NotificationManagerCompat) {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE_DEFAULT)
        notificationManagerCompat.createNotificationChannel(channel)
    }

    private fun sendServiceToActivity(action: Int) {
        val intent = Intent(KEY_SEND_ACTIVITY)
        val songJson = Gson().toJson(song)
        intent.putExtra(KEY_SONG_TO_ACTIVITY, songJson)
        intent.putExtra(KEY_ACTION_TO_ACTIVITY, action)
        intent.putExtra(KEY_IS_PLAY_TO_ACTIVITY, isPlay)
        intent.putExtra(KEY_SEND_RECEIVER, action)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun onNextSong(listSongs:List<Song>) {
        listSongs.forEach {
            if (song.filePath == it.filePath) {
                song = it
            }
        }
        var indexSong = listSongs.indexOf(song)
        if (indexSong < listSongs.size - 1) {
            indexSong += 1
        } else if (indexSong == listSongs.size - 1) {
            nextSong(listSongs.first())
            return
        }
        nextSong(listSongs[indexSong])
    }

    override fun nextSong(songNew: Song) {
        singletonApp.songPlay= songNew
        song = songNew
    }

    override fun previousSong(songNew: Song) {
        singletonApp.songPlay= songNew
        song = songNew
    }

    private fun onPreviousSong(listSongs: List<Song>) {
        listSongs.forEach {
            if (song.filePath == it.filePath) {
                song = it
            }
        }
        var indexSong = listSongs.indexOf(song)
        if (indexSong == 0) {
            previousSong(listSongs.last())
            return
        } else if (indexSong < listSongs.size) {
            indexSong -= 1
        }
        previousSong(listSongs[indexSong])

    }

    override fun onDestroy() {
        super.onDestroy()
        if (isPlay == true) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}