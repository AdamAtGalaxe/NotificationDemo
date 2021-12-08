package com.galaxe.notificationdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi




class MainActivity : AppCompatActivity() {
    lateinit var notificatonManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder

    private var channelid = "my.shrey.notification"
    var description = "Testing Notification"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn =  findViewById<Button>(R.id.btn)

        notificatonManager =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn.setOnClickListener(View.OnClickListener {
            var intent = Intent(this,A2::class.java)

            var pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){

                notificationChannel = NotificationChannel(channelid,description, NotificationManager.IMPORTANCE_HIGH)

                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(true)
                notificatonManager.createNotificationChannel(notificationChannel)
                builder = Notification.Builder(this,channelid)
                    .setContentTitle("My Own Notification")
                    .setContentText("You have 3 messages from Notification app")
                    .setSmallIcon(android.R.drawable.btn_star)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
            }
            notificatonManager.notify(1234,builder.build())
        })


    }

}