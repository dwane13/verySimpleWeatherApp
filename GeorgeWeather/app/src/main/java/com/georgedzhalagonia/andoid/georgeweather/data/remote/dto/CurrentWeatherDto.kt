package com.georgedzhalagonia.andoid.georgeweather.data.remote.dto

import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional.*
import com.georgedzhalagonia.andoid.georgeweather.domain.model.CurrentWeather

data class CurrentWeatherDto(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Temperature,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun CurrentWeatherDto.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(clouds = clouds, weather = weather, wind = wind, city = name, temperature = main)
}