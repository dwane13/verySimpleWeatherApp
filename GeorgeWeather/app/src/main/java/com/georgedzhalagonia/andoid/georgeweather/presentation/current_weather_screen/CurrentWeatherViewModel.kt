package com.georgedzhalagonia.andoid.georgeweather.presentation.current_weather_screen

import android.location.Location
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgedzhalagonia.andoid.georgeweather.common.Constants
import com.georgedzhalagonia.andoid.georgeweather.domain.model.CurrentWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.domain.usecases.CurrentWeatherUseCase
import com.georgedzhalagonia.andoid.georgeweather.domain.usecases.GetLocationUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val locationUseCase: GetLocationUseCase
) : ViewModel() {


    private val _locationState = mutableStateOf<Operation<Location>>(Operation.Pending())
    val locationState: State<Operation<Location>> = _locationState

    private val _weatherState = mutableStateOf<Operation<CurrentWeather>>(Operation.Pending())
    val weatherState: State<Operation<CurrentWeather>> = _weatherState

    fun getUserLocation() = viewModelScope.launch {
        locationUseCase().collectLatest {
            _locationState.value = it
        }
    }

    fun getCurrentWeather(lat: Double, lon: Double) = viewModelScope.launch {
        currentWeatherUseCase(lat, lon, Constants.API_KEY).collectLatest {
            _weatherState.value = it
        }
    }
}