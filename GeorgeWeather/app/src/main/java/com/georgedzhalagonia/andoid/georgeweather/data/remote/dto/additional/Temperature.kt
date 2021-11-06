package com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional

data class Temperature(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    var temp: Double,
    val temp_max: Double,
    val temp_min: Double
)