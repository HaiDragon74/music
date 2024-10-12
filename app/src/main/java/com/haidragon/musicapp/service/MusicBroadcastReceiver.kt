package com.haidragon.musicapp.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.haidragon.musicapp.presentation.utils.Constant
import com.haidragon.musicapp.presentation.utils.Constant.KEY_SEND_SERVICE

class MusicBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.getIntExtra(Constant.KEY_SEND_RECEIVER, 0)
        if (action != null) {
            val intentAction = Intent(context, MusicService::class.java)
            intentAction.putExtra(KEY_SEND_SERVICE, action)
            context?.startService(intentAction)
        }
    }
}