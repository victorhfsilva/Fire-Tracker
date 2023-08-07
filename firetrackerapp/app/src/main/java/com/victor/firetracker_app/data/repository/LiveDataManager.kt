package com.victor.firetracker_app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object LiveDataManager {
    // Declare your LiveData instances here
    private val _temperature = MutableLiveData<String>()
    private val _isOnFire = MutableLiveData<Boolean>()
    private val _lastIsOnFire = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()

    // Expose public LiveData getters
    val temperature: LiveData<String> get() = _temperature
    val isOnFire: LiveData<Boolean> get() = _isOnFire
    val lastIsOnFire: LiveData<Boolean> get() = _lastIsOnFire
    val isLoading: LiveData<Boolean> get() = _isLoading

    // Functions to update LiveData values
    fun updateTemperature(temperature: String) {
        _temperature.postValue(temperature)
    }

    fun updateIsOnFire(isOnFire: Boolean) {
        _isOnFire.postValue(isOnFire)
    }

    fun updateIsLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }

    fun updateLastIsOnFire(lastIsOnFire: Boolean){
        _lastIsOnFire.postValue(lastIsOnFire)
    }


}