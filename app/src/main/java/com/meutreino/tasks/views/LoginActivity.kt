package com.meutreino.tasks.views

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.devmasterteam.tasks.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val mViewHolder = ViewHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa elementos
        this.mViewHolder.editEmail = this.findViewById<View>(R.id.edit_email) as EditText
        this.mViewHolder.editPassword = this.findViewById<View>(R.id.edit_password) as EditText
        this.mViewHolder.buttonLogin = this.findViewById<View>(R.id.button_login) as Button
        this.mViewHolder.textRegister = this.findViewById<View>(R.id.text_register) as TextView

        // Inicializa eventos
        this.mViewHolder.buttonLogin!!.setOnClickListener(this)
        this.mViewHolder.textRegister!!.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_login) {
            startActivity(Intent(this, MainActivity::class.java))
        } else if (id == R.id.text_register) {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    /**
     * ViewHolder
     */
    private class ViewHolder {
        var editEmail: EditText? = null
        var editPassword: EditText? = null
        var buttonLogin: Button? = null
        var textRegister: TextView? = null
    }

    class AlarmReceiver : BroadcastReceiver() {
        internal lateinit var notificationManager: NotificationManager
        internal lateinit var myNotification: Notification
        private val myBlog = "http://android-er.blogspot.com/"

        @SuppressLint("WrongConstant")
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show()

            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(myBlog))
            val pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    myIntent,
                    Intent.FLAG_ACTIVITY_NEW_TASK)

            myNotification = NotificationCompat.Builder(context)
                    .setContentTitle("Exercise of Notification!")
                    .setContentText("http://android-er.blogspot.com/")
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
}
