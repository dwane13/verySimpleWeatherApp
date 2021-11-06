package com.georgedzhalagonia.andoid.georgeweather.presentation

sealed class Navigation(val route: String) {
    object Permission: Navigation("permission_screen")
    object CurrentWeather: Navigation("current_weather_screen")
    object DailyWeather: Navigation("daily_weather_screen")
}