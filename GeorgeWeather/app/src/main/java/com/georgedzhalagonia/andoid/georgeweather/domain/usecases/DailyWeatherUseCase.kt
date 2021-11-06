package com.georgedzhalagonia.andoid.georgeweather.domain.usecases

import com.georgedzhalagonia.andoid.georgeweather.domain.model.CurrentWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.DailyWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Response
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.errorOperation
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.successOperation
import com.georgedzhalagonia.andoid.georgeweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DailyWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(lat: Double, lon: Double, appId: String): Flow<Operation<DailyWeather>> = flow {
        emit(Operation.Loading<DailyWeather>())

        when (val response = repository.getDailyWeather(lat, lon, appId)) {
            is Response.Error -> {
                emit(response.errorOperation())
            }
            is Response.Success -> {
                emit(response.successOperation())
            }
        }
    }
}