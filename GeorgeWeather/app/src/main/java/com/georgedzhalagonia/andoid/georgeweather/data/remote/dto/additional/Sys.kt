package com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional

data class Sys(
    val country: String,
    val id: Int,
    val message: Double? = null,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)