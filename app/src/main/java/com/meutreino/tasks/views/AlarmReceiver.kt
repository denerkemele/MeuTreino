package com.meutreino.tasks.views

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.widget.Toast

import com.devmasterteam.tasks.R

class AlarmReceiver : BroadcastReceiver() {
    internal var notificationManager: NotificationManager
    internal var myNotification: Notification


    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Notificação Recebida!", Toast.LENGTH_LONG).show()

        //Intent myIntent = new Intent(Intent., Uri.parse(#var));

        //Intent myIntent = new Intent(Intent.ACTION_SEND);

        val inten = Intent(context, MainActivity::class.java)
        //inten.putExtra("mensagem", "Alarme disparado");


        val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                inten,
                Intent.FLAG_ACTIVITY_NEW_TASK)

        myNotification = NotificationCompat.Builder(context)
                .setContentTitle("Notificação!")
                .setContentText("HORA DE TREINAR")
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher)
                .build()

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification)
    }

    companion object {

        private val MY_NOTIFICATION_ID = 1
    }


}
