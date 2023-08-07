package com.victor.firetracker_app.events.foreground_services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.telephony.SmsManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.victor.firetracker_app.R
import com.victor.firetracker_app.data.repository.LiveDataManager
import com.victor.firetracker_app.data.repository.SharedPreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DataRequestForegroundService : Service() {

    @Inject
    lateinit var sharedPreferencesManager : SharedPreferencesManager

    var isDifferentFromLast: Boolean = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(FOREGROUND_SERVICE_NOTIFICATION_ID, createForegroundNotification())
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        LiveDataManager.isOnFire.observeForever{isOnFire ->
            LiveDataManager.lastIsOnFire.observeForever{lastIsOnFire ->
                isDifferentFromLast = isOnFire != lastIsOnFire
            }

            if (isOnFire && sharedPreferencesManager.getIsNotificationsAllowed()) {
                if (isDifferentFromLast) {
                    val notificationId = ALERT_NOTIFICATION_ID // Identificador único para a notificação
                    val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.notify(notificationId, createAlertNotification())
                }
            }
            /*else {
                cancelAlertNotification()
            }*/

            if (isOnFire && sharedPreferencesManager.getIsContactAlertsAllowed()){
                if (isDifferentFromLast){
                    sharedPreferencesManager.getContact()?.let {sendSMS(it)}
                }
            }

            LiveDataManager.updateLastIsOnFire(isOnFire)
        }

    }

    fun sendSMS(destination: String) {
        val message = "Fire Tracker: Alerta, detectamos indícios de incêndio!"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(destination, null, message, null, null)
                Log.d("SMSMessage:", "The message was send successfully.")
            } catch (e: Exception) {
                Log.e("SMSMessage:", "Error sending the message.")
                e.printStackTrace()
            }
        }
    }


    private fun cancelAlertNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(ALERT_NOTIFICATION_ID)
    }

    private fun createForegroundNotification(): Notification {
        val channelId = "data_request_channel"
        val notificationTitle = "Fire Tracker"
        val notificationText = "Fire Tracker está rodando em Background."

        val notificationBuilder = createNotificationBuilder(channelId, notificationTitle, notificationText)

        return notificationBuilder.build()

    }

    private fun createAlertNotification(): Notification {

        val channelId = "data_request_channel"
        val notificationTitle = "Fire Tracker: Alerta"
        val notificationText = "Detectamos Indícios de Incêndio."

        val notificationBuilder = createNotificationBuilder(channelId, notificationTitle, notificationText)

        return notificationBuilder.build()

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "data_request_channel"
            val channelName = "Data Request Channel"
            val channelDescription = "Channel for Data Request Foreground Service"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = channelDescription

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotificationBuilder(
        channelId: String,
        notificationTitle: String,
        notificationText: String
    ): NotificationCompat.Builder {
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setSmallIcon(R.drawable.fire_tracker_logo) // Replace with your notification icon
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
            notificationBuilder.setChannelId(channelId)
        }
        return notificationBuilder
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        private const val FOREGROUND_SERVICE_NOTIFICATION_ID = 12345
        private const val ALERT_NOTIFICATION_ID = 505
    }
}