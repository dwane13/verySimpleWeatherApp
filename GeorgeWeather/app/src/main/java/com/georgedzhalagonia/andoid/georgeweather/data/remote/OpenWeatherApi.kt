package com.georgedzhalagonia.andoid.georgeweather.data.remote

import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.CurrentWeatherDto
import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.DailyWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("appid") apiKey: String
    ): CurrentWeatherDto


    @GET("data/2.5/forecast/daily")
    fun getWeatherDataDaily(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("appid") apiKey: String
    ): DailyWeatherDto
}