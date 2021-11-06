package com.georgedzhalagonia.andoid.georgeweather.presentation.current_weather_screen.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.georgedzhalagonia.andoid.georgeweather.domain.model.CurrentWeather
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.presentation.current_weather_screen.CurrentWeatherViewModel
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.dark_middle_blue
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.grey


@Composable
fun TopBarInfo(
    weatherData: CurrentWeather,
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val iconState = viewModel.iconState.value

    Row(Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(20.dp)
                .padding(top = 40.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .requiredHeight(80.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = weatherData.city,
                    style = MaterialTheme.typography.h5,
                    color = dark_middle_blue
                )
                when (iconState) {
                    is Operation.Error -> {
                        Button(onClick = {
                            viewModel.getWeatherIcon(weatherData.weather.first().icon)
                        }) {

                        }
                    }
                    is Operation.Loading -> CircularProgressIndicator()
                    is Operation.Success -> {
                        WeatherIcon(icon = iconState.data.asImageBitmap())
                    }
                    is Operation.NotStarted -> viewModel.getWeatherIcon(weatherData.weather.first().icon)
                }
            }
            Text(
                text = "${weatherData.temperature.temp}°",
                style = MaterialTheme.typography.h3,
                color = dark_middle_blue
            )
            Box(
                modifier = Modifier
                    .background(grey, shape = RoundedCornerShape(20.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = weatherData.weather.first().main,
                    color = dark_middle_blue,
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}


@Composable
fun WeatherIcon(icon: ImageBitmap) {
    Image(bitmap = icon, contentDescription = "weather_icon")
}





