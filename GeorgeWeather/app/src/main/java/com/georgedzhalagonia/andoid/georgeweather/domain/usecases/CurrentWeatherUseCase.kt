package com.georgedzhalagonia.andoid.georgeweather.domain.usecases

import com.georgedzhalagonia.andoid.georgeweather.domain.model.CurrentWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Response
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.successOperation
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.errorOperation
import com.georgedzhalagonia.andoid.georgeweather.domain.repository.WeatherRepository
import com.georgedzhalagonia.andoid.georgeweather.common.util.toCelsius
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(lat: Double, lon: Double, appId: String): Flow<Operation<CurrentWeather>> = flow {
        emit(Operation.Loading<CurrentWeather>())

        when (val response = repository.getCurrentWeather(lat, lon, appId)) {
            is Response.Error -> {
                emit(response.errorOperation())
            }
            is Response.Success -> {
                emit(response.successOperation().apply {
                    data.temperature.apply {
                        temp = temp.toCelsius()
                    }
                })
            }
        }
    }
}