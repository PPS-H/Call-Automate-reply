package com.example.callautomatereply.Services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.callautomatereply.Application.MyApp
import com.example.callautomatereply.MainActivity
import com.example.callautomatereply.R
import com.example.callautomatereply.model

class MyService:Service(){
    var obj: model?=null
    var intentFilter:IntentFilter?=null
    var MyBroadcast:PhoneCallReceiver?=null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        var state=intent?.getStringExtra("state")
        var data=intent?.getStringExtra("data")

        obj= model(state,data)
        MyBroadcast= PhoneCallReceiver(obj!!)
        intentFilter = IntentFilter()
        intentFilter!!.addAction("android.intent.action.PHONE_STATE")
        registerReceiver(MyBroadcast, intentFilter)

        val Notification_Intent=Intent(this,MainActivity::class.java)
        Notification_Intent.putExtra("startup_id","fromNotification")
        var pendingIntent=PendingIntent.getActivity(this,
        0,Notification_Intent,0)

        var notification=NotificationCompat.Builder(this,MyApp.Channel_ID)
                .setContentTitle("Your app is running")
                .setContentText("If you want to stop the app,then click here")
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(1,notification)

        return START_NOT_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(MyBroadcast)

    }
}