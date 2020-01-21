package com.kaito.quickaction

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person

class CreateNotes : AppCompatActivity(){

    private val CHANNEL_ID = "Notes"
    private val person1 = Person.Builder()
            .setName("Kaito")
            .build()
    private val person2 = Person.Builder()
            .setName("Friend A")
            .build()

    /*private var message = NotificationCompat.MessagingStyle("Me")
            .setConversationTitle("Random stuff")
            .addMessage("Hi", 1, person1) // Pass in null for user.
            .addMessage("What's up?", 2, person2)
            .addMessage("Not much", 3, person1)
            .addMessage("How about lunch?", 4, person2)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        Thread(Runnable{
            val searchTarget = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
            var content = NotificationCompat.MessagingStyle("Me")



            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_polymer_black_24dp)
                    .setStyle(NotificationCompat.MessagingStyle("Me")
                            .addMessage(searchTarget, 1, if ((0..1).random() == 0) person1 else person2))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(1, builder.build())
            }
        }).start()

        finish()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Default"
            val descriptionText = "Default notification channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
