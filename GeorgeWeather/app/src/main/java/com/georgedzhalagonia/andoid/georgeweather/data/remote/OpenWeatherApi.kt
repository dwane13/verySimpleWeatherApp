package com.georgedzhalagonia.andoid.georgeweather.data.remote

import com.georgedzhalagonia.andoid.georgeweather.common.Constants
import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.CurrentWeatherDto
import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.DailyWeatherDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("appid") apiKey: String
    ): CurrentWeatherDto


    @GET("data/2.5/forecast/daily")
    suspend fun getWeatherDataDaily(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("appid") apiKey: String
    ): DailyWeatherDto

    @GET("${Constants.IMAGE_URL}img/wn/{ICON_ID}@4x.png")
    suspend fun getWeatherIcon(
        @Path("ICON_ID") iconId: String
    ): ResponseBody
}