package com.georgedzhalagonia.andoid.georgeweather.presentation.current_weather_screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.presentation.Navigation
import com.georgedzhalagonia.andoid.georgeweather.presentation.components.CenteredProgressIndicator
import com.georgedzhalagonia.andoid.georgeweather.presentation.current_weather_screen.Component.ErrorDialog
import com.georgedzhalagonia.andoid.georgeweather.presentation.current_weather_screen.Component.TopBarInfo

@Composable
fun CurrentWeatherScreen(
    navController: NavController,
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val locationState = viewModel.locationState.value
    val weatherState = viewModel.weatherState.value

    val isLocationFetched = remember { mutableStateOf(false) }
    val latitude = remember { mutableStateOf(0.0) }
    val longitude = remember { mutableStateOf(0.0) }

    if (ContextCompat.checkSelfPermission(
            LocalContext.current,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        navController.navigate(Navigation.Permission.route)
    }

    when (locationState) {
        is Operation.Error -> {
            isLocationFetched.value = false
            ErrorDialog("Не удалось получить текущее местоположение") {
                viewModel.getUserLocation()
            }
        }
        is Operation.Loading -> {
            isLocationFetched.value = false
            CenteredProgressIndicator()
        }
        is Operation.NotStarted -> {
            viewModel.getUserLocation()
        }
        is Operation.Success -> {
            if (!isLocationFetched.value) {
                isLocationFetched.value = true
                latitude.value = locationState.data.latitude
                longitude.value = locationState.data.longitude
                viewModel.getCurrentWeather(latitude.value, longitude.value)
            }

        }
    }

    when (weatherState) {
        is Operation.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                ErrorDialog("Не удалось получить данные о погоде") {
                    viewModel.getCurrentWeather(latitude.value, longitude.value)
                }
            }
        }
        is Operation.Loading -> CenteredProgressIndicator()
        is Operation.NotStarted -> {
        }
        is Operation.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                TopBarInfo(weatherData = weatherState.data)
            }
        }
    }
}