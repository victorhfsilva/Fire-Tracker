package com.victor.firetracker_app.data.network

import com.victor.firetracker_app.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface Requests {

    @GET("getLast")
    fun getLast(): Call<Data>
}