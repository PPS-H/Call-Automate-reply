package com.example.callautomatereply.Application

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import java.nio.channels.Channel

class MyApp:Application() {
    companion object{
        var Channel_ID="NotificationChannelId"
    }
    override fun onCreate() {
        super.onCreate()
        createNotifications()
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotifications() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var channel=NotificationChannel(
                    Channel_ID,
                    "Foreground Service Notification",
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            var manager=getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}