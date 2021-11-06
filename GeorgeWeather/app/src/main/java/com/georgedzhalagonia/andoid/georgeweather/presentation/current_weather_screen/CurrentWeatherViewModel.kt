package com.georgedzhalagonia.andoid.georgeweather.presentation.current_weather_screen

import android.graphics.Bitmap
import android.location.Location
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgedzhalagonia.andoid.georgeweather.common.Constants
import com.georgedzhalagonia.andoid.georgeweather.domain.model.CurrentWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.UserLocation
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.domain.usecases.CurrentWeatherUseCase
import com.georgedzhalagonia.andoid.georgeweather.domain.usecases.GetLocationUseCase
import com.georgedzhalagonia.andoid.georgeweather.domain.usecases.GetWeatherIconUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val locationUseCase: GetLocationUseCase,
    private val iconUseCase: GetWeatherIconUseCase
) : ViewModel() {


    private val _locationState = mutableStateOf<Operation<UserLocation>>(Operation.NotStarted())
    val locationState: State<Operation<UserLocation>> = _locationState

    private val _weatherState = mutableStateOf<Operation<CurrentWeather>>(Operation.NotStarted())
    val weatherState: State<Operation<CurrentWeather>> = _weatherState

    private val _iconState = mutableStateOf<Operation<Bitmap>>(Operation.NotStarted())
    val iconState: State<Operation<Bitmap>> = _iconState

    fun getUserLocation() = viewModelScope.launch {
        locationUseCase().collectLatest {
            _locationState.value = it
        }
    }

    fun getWeatherIcon(iconId: String) = viewModelScope.launch {
        iconUseCase(iconId).collectLatest {
            _iconState.value = it
        }
    }

    fun getCurrentWeather(lat: Double, lon: Double) = viewModelScope.launch {
        currentWeatherUseCase(lat, lon, Constants.API_KEY).collectLatest {
            _weatherState.value = it
        }
    }
}