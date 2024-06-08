package com.remotetechs.musicapp.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
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
import com.remotetechs.musicapp.activity.PlayMusicActivity
import com.remotetechs.musicapp.R
import com.remotetechs.musicapp.model.Song

class MusicService : Service() {
    private var currentSong: Song? = null

    companion object {
        var mediaPlayer: MediaPlayer? = null
        var song = Song()
        var isPlay: Boolean? = null
        var isService: Boolean? = null
        const val KEY_DATA_SONG = "data_song"
        const val CHANNEL_NAME = "channel_name"
        const val CHANNEL_ID = "channel_id"
        const val KEY_SEND_RECEIVER = "key_send_receiver"
        const val KEY_SEND_ACTIVITY = "key_send_activity"
        const val PREVIOUS = 1
        const val PAUSE = 2
        const val NEXT = 3
        const val RESUME = 4
        const val CLOSE = 5
        const val START = 6
        const val RELOAD = 7
        const val SEEKBAR = 8
        const val KEY_SONG_TO_ACTIVITY = "key_song_to_activity"
        const val KEY_IS_PLAY_TO_ACTIVITY = "key_is_play_to_activity"
        const val KEY_ACTION_TO_ACTIVITY = "key_action_to_action"

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
            if (song.filePath != currentSong?.filePath) {
                startMusic(song)
                currentSong = song
            }
            sendNotificationMusic(song)
        }
        val action = intent?.getIntExtra(MusicBroadcastReceiver.KEY_SEND_SERVICE, 0)
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
        song = onPreviousSong()
        mediaPlayer = MediaPlayer.create(applicationContext, song.filePath?.toUri())
        mediaPlayer?.start()
        isPlay = true
        startMusic(song)
        sendNotificationMusic(song)
        sendServiceToActivity(PAUSE)
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
        song = onNextSong()
        mediaPlayer = MediaPlayer.create(applicationContext, song.filePath?.toUri())
        mediaPlayer?.start()
        isPlay = true
        startMusic(song)
        sendNotificationMusic(song)
        sendServiceToActivity(NEXT)
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
        sendServiceToActivity(CLOSE)
    }

    private fun startMusic(song: Song) {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.release()
            mediaPlayer = null
            isPlay = false
        }
        mediaPlayer = MediaPlayer.create(applicationContext, song.filePath?.toUri())
        mediaPlayer?.start()
        isPlay = true
        isService = true
        mediaPlayer?.setOnCompletionListener {
            nextMusic()
        }
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
        if (song.coverArt?.isPremultiplied == true) {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_img_avt)
            notificationBuilder.setLargeIcon(bitmap)
        } else
            notificationBuilder.setLargeIcon(song.coverArt)
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK) == PackageManager.PERMISSION_GRANTED
            )
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForeground(1, notificationBuilder.build(), FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
                }
            }

        }
        else{
            startForeground(1,notificationBuilder.build())
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

    private fun onNextSong(): Song {
        val listSong = song.getSongLocal(this)
        listSong.forEach {
            if (song.filePath == it.filePath) {
                song = it
            }
        }
        var indexSong = listSong.indexOf(song)
        if (indexSong < listSong.size - 1) {
            indexSong += 1
        } else if (indexSong == listSong.size - 1) {
            return listSong.first()
        }
        return listSong[indexSong]
    }


    private fun onPreviousSong(): Song {
        val listSong = song.getSongLocal(this)
        listSong.forEach {
            if (song.filePath == it.filePath) {
                song = it
            }
        }
        var indexSong = listSong.indexOf(song)
        if (indexSong == 0) {
            return listSong.last()
        } else if (indexSong < listSong.size) {
            indexSong -= 1
        }
        return listSong[indexSong]
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isPlay == true) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}