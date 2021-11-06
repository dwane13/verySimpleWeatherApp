package com.example.composeexample.ui.theme

import android.hardware.lights.Light
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.*

private val LightColorPalette = lightColors(
        primary = dark_middle_blue,
        surface = white,
        secondary = orange
)

@Composable
fun GeorgeWeatherTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = LightColorPalette

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}