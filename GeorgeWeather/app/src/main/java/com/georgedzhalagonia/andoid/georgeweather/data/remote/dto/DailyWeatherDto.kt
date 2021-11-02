package com.georgedzhalagonia.andoid.georgeweather.data.remote.dto

import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional.City
import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional.Information
import com.georgedzhalagonia.andoid.georgeweather.domain.model.DailyWeather

data class DailyWeatherDto(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Information>,
    val message: Double
)

fun DailyWeatherDto.toDailyWeather() = DailyWeather(
    weatherList = list
)