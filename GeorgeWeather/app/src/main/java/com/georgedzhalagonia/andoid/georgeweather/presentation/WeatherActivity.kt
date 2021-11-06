package com.georgedzhalagonia.andoid.georgeweather.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.example.composeexample.ui.theme.GeorgeWeatherTheme
import com.georgedzhalagonia.andoid.georgeweather.presentation.current_weather_screen.CurrentWeatherScreen
import com.georgedzhalagonia.andoid.georgeweather.presentation.permission_screen.PermissionScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author George Dzhalagoniya
 * @version 0.0.3
 */
@AndroidEntryPoint
class WeatherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeorgeWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Navigation.CurrentWeather.route) {
                        composable(Navigation.CurrentWeather.route) {
                            CurrentWeatherScreen(navController)
                        }
                        composable(Navigation.DailyWeather.route) {
                            // TODO: 05.11.2021 DailyWeatherScreen
                        }
                        composable(Navigation.Permission.route) {
                            PermissionScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


