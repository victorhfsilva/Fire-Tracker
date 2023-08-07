package com.victor.firetracker_app.model

data class Data (
    val id: Long,
    val insertionDateTime: String,
    val irLevel: Double,
    val temperature: Double,
    val onFire: Boolean
    )