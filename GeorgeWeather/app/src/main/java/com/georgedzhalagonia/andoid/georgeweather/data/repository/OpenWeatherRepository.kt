package com.georgedzhalagonia.andoid.georgeweather.data.repository

import com.georgedzhalagonia.andoid.georgeweather.data.remote.OpenWeatherApi
import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.toCurrentWeather
import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.toDailyWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.CurrentWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.DailyWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Response
import com.georgedzhalagonia.andoid.georgeweather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import javax.inject.Inject

class OpenWeatherRepository @Inject constructor(
    private val weatherApi: OpenWeatherApi
) : WeatherRepository {


    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        appId: String
    ): Response<CurrentWeather> {
        return withContext(Dispatchers.IO) {
            try {
                val response = weatherApi.getCurrentWeather(lat, lon, appId)
                Response.Success(response.toCurrentWeather())
            } catch (e: HttpException) {
                Response.Error(e, e.message ?: "Cant reach server. Check your internet connection")
            } catch (e: Exception) {
                Response.Error(e, e.message ?: "Unexpected error occured")
            }
        }
    }

    override suspend fun getDailyWeather(
        lat: Double,
        lon: Double,
        appId: String
    ): Response<DailyWeather> {
        return try {
            val response = weatherApi.getWeatherDataDaily(lat, lon, appId)
            Response.Success(response.toDailyWeather())
        } catch (e: HttpException) {
            Response.Error(e, e.message ?: "Cant reach server. Check your internet connection")
        } catch (e: Exception) {
            Response.Error(e, e.message ?: "Unexpected error occured")
        }
    }

    override suspend fun getWeatherIcon(iconId: String): Response<ResponseBody> {
        return try {
            val response = weatherApi.getWeatherIcon(iconId)
            Response.Success(response)
        } catch (e: HttpException) {
            Response.Error(e, e.message ?: "Cant reach server. Check your internet connection")
        } catch (e: Exception) {
            Response.Error(e, e.message ?: "Unexpected error occured")
        }
    }
}