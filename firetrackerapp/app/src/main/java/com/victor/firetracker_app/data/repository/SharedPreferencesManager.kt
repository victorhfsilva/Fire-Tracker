package com.victor.firetracker_app.data.repository

import android.content.Context

class SharedPreferencesManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("configurations", Context.MODE_PRIVATE)

    fun allowNotifications(isActive : Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean("notifications", isActive)
        editor.apply()
    }

    fun setServer(server: String){
        val editor = sharedPreferences.edit()
        editor.putString("server", server)
        editor.apply()
    }

    fun allowContactAlerts(isActive: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean("contact_alert", isActive)
        editor.apply()
    }

    fun setContact(contact: String){
        val editor = sharedPreferences.edit()
        editor.putString("contact", contact)
        editor.apply()
    }


    fun getIsNotificationsAllowed(): Boolean  = sharedPreferences.getBoolean("notifications", true)

    fun getServer(): String? = sharedPreferences.getString("server", "http://192.168.12.13:49152/firetracker/data/getLast")

    fun getIsContactAlertsAllowed(): Boolean = sharedPreferences.getBoolean("contact_alert", false)

    fun getContact(): String? = sharedPreferences.getString("contact", "")

}
