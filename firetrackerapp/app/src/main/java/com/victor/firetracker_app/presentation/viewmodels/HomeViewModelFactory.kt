package com.victor.firetracker_app.presentation.viewmodels

import android.app.Application
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(private val application: Application, private val view: View)
    : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(application, view) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}