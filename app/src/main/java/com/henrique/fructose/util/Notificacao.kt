package com.henrique.fructose.util

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.henrique.fructose.R
import com.henrique.fructose.R.string.*

class Notificacao {

    companion object {

        private const val NOTIFICATION_TAG = "NewMessage"
        private const val NOTIFICATION_DEFAULT = "primary"


        fun notify(context: Context, tittle: String,
                   texto: String) {
            val res = context.resources
            val picture = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round)
            val builder = NotificationCompat.Builder(context, NOTIFICATION_DEFAULT)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentTitle(tittle)
                    .setContentText(texto)
                    .setChannelId(NOTIFICATION_DEFAULT)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                     .setLargeIcon(picture)
                     .setSmallIcon(R.drawable.ic_watermelon)
                    .setTicker(texto)
                    .setContentIntent(
                            PendingIntent.getActivity(
                                    context,
                                    0,
                                    Intent(Intent.EXTRA_EMAIL),
                                    PendingIntent.FLAG_UPDATE_CURRENT))

                    .setStyle(NotificationCompat.BigTextStyle()
                            .bigText(texto)
                            .setBigContentTitle(tittle)
                            .setSummaryText("Fructose"))
                    .setAutoCancel(true)

            notify(context, builder.build())
        }

        private fun notify(context: Context, notification: Notification) {
            val nm = context
                    .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                nm.notify(NOTIFICATION_TAG, 0, notification)
        }


        fun cancel(context: Context) {
            val nm = context
                    .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                nm.cancel(NOTIFICATION_TAG, 0)
        }

        fun notificacaoSemInternet(context: Context) {
            val res = context.resources
            //val picture = BitmapFactory.decodeResource(res, R.drawable.icon)
            val texto = context.getString(no_internet)
            val tittle = context.getString(conect_internet)
            val builder = NotificationCompat.Builder(context)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    //.setSmallIcon(R.drawable.ic_about)
                    .setContentTitle(tittle)
                    .setContentText(texto)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    //.setLargeIcon(picture)
                    .setTicker(texto)
                    .setStyle(NotificationCompat.BigTextStyle()
                            .bigText(texto)
                            .setBigContentTitle(tittle)
                            .setSummaryText(context.getString(no_internet)))
                    // Automatically dismiss the notification when it is touched.
                    .setAutoCancel(true)

            notify(context, builder.build())
        }


    }
}
