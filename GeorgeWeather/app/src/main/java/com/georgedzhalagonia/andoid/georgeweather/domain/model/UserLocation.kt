package com.georgedzhalagonia.andoid.georgeweather.domain.model

import android.location.Location

data class UserLocation(
    val longitude: Double,
    val latitude: Double
)

fun Location.toUserLocation() = UserLocation(this.longitude, this.latitude)