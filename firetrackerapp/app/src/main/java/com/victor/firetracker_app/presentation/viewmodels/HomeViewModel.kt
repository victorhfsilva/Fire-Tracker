package com.victor.firetracker_app.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.victor.firetracker_app.data.network.RetrofitClient
import com.victor.firetracker_app.data.repository.LiveDataManager
import com.victor.firetracker_app.model.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class HomeViewModel(application: Application, private val view: View) : AndroidViewModel(application) {

    val temperature: LiveData<String> get() = LiveDataManager.temperature
    val isOnFire: LiveData<Boolean> get() = LiveDataManager.isOnFire
    val isLoading: LiveData<Boolean> get() = LiveDataManager.isLoading


    fun startPeriodicDataRequest() {
        requestData() // Make the initial request
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                requestData() // Make the request every second
                handler.postDelayed(this, 1000) // Schedule the next execution
            }
        }
        handler.postDelayed(runnable, 1000) // Start the periodic execution
    }

    fun requestData() {
        if (isInternetAvailable(getApplication())){
            getData()
        }
        else {
            Log.e("Network", "No Internet Connection")
        }
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            LiveDataManager.updateIsLoading(true)
            try {
                val response = RetrofitClient.requests.getLast().execute()
                Log.d("DataRequest", response.toString())
                if (response.isSuccessful) {
                    getResponseData(response)
                } else {
                    Log.e("RetrofitError", "${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("DataRequest", "Error: ${e.message}")
                Snackbar.make(view,"Não foi possível obter uma resposta." , Snackbar.LENGTH_SHORT).show()
                LiveDataManager.updateIsLoading(false)
            }
        }
    }

    private suspend fun getResponseData(response: Response<Data>) {
        val data = response.body()
        Log.d("DataRequest", data.toString())
        val temperatureString = data?.temperature?.toString() ?: "  "
        val isOnfire = data?.onFire ?: throw Exception("Null data.")
        withContext(Dispatchers.Main) {
            LiveDataManager.updateTemperature(temperatureString)
            LiveDataManager.updateIsLoading(false)
            LiveDataManager.updateIsOnFire(isOnfire)
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}

