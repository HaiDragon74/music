package com.remotetechs.musicapp.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MusicBroadcastReceiver : BroadcastReceiver() {
    companion object {
        const val KEY_SEND_SERVICE = "key_send_service"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.getIntExtra(MusicService.KEY_SEND_RECEIVER, 0)
        if (action != null) {
            val intentAction = Intent(context, MusicService::class.java)
            intentAction.putExtra(KEY_SEND_SERVICE, action)
            context?.startService(intentAction)
        }
    }
}