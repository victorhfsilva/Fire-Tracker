package com.victor.firetracker_app.events.workers

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.victor.firetracker_app.presentation.viewmodels.HomeViewModel

class RequestDataWorker (context: Context, params: WorkerParameters) : Worker(context, params) {

    private val homeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(applicationContext as Application).create(HomeViewModel::class.java)

    override fun doWork(): Result {
        homeViewModel.requestData()
        Log.d("BackgroundTask", "Request Data Successful.")
        return Result.success()
    }

}