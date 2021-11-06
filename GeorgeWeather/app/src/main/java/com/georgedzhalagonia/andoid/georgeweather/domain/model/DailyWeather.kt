package com.georgedzhalagonia.andoid.georgeweather.domain.model

import com.georgedzhalagonia.andoid.georgeweather.data.remote.dto.additional.Information

data class DailyWeather(
    val weatherList: List<Information>
)