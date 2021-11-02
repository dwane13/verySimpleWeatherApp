package com.georgedzhalagonia.andoid.georgeweather.presentation.current_weather_screen.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.georgedzhalagonia.andoid.georgeweather.domain.model.CurrentWeather
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.dark_middle_blue
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.grey


@Composable
fun TopBarInfo(
    weatherData: CurrentWeather
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(20.dp)
                .padding(top = 40.dp)
        ) {
            Text(
                text = weatherData.city,
                style = MaterialTheme.typography.h4,
                color = dark_middle_blue
            )
            Text(
                text = "${weatherData.temperature.temp}°",
                style = MaterialTheme.typography.h2,
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





