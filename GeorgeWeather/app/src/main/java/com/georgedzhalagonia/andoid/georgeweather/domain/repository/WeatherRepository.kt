package com.georgedzhalagonia.andoid.georgeweather.domain.repository

import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Response
import com.georgedzhalagonia.andoid.georgeweather.domain.model.CurrentWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.DailyWeather

interface WeatherRepository {

    suspend fun getCurrentWeather(lat: Double, lon: Double, appId: String): Response<CurrentWeather>

    suspend fun getDailyWeather(lat: Double, lon: Double, appId: String): Response<DailyWeather>

}