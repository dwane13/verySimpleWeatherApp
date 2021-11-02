package com.georgedzhalagonia.andoid.georgeweather.domain.model

import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional.Clouds
import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional.Temperature
import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional.Weather
import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional.Wind

data class CurrentWeather(
    val temperature: Temperature,
    val city: String,
    val clouds: Clouds,
    val weather: List<Weather>,
    val wind: Wind
)