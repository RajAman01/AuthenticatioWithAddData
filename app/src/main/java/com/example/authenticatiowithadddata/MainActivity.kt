package com.example.authenticatiowithadddata

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private var channelId = "com.example.authenticatiowithadddata"
    private var description = "Test Manager"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        Button.setOnClickListener {

            val intent = Intent(this, LauncherActivity::class.java)
            val pendintIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


            val contentView = RemoteViews(packageName, R.layout.notification_card)
            contentView.setTextViewText(R.id.card_tv_title, "Hello Buddy")
            contentView.setTextViewText(R.id.card_tv_description, "This Is new Notification ")

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId).setContentTitle("Code Android")
                    .setContent(contentView)
                    .setContentText("My Notification Activity")
                    .setSmallIcon(R.mipmap.ic_launcher_round).setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.mipmap.ic_launcher
                        )
                    )
                    .setContentIntent(pendintIntent)
            } else {
                builder = Notification.Builder(this).setContentTitle("Code Android")
                    .setContent(contentView)
                    .setContentText("My Notification Activity")
                    .setSmallIcon(R.mipmap.ic_launcher_round).setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.mipmap.ic_launcher
                        )
                    )
                    .setContentIntent(pendintIntent)
            }
            notificationManager.notify(1234, builder.build())
        }


    }
}

