package com.georgedzhalagonia.andoid.georgeweather.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


/**
 * @author George Dzhalagoniya
 * @version 0.0.3
 */
class WeatherActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }


    @Composable
    fun MainScreen() {

    }

    @Preview
    @Composable
    fun Preview() {
        MainScreen()
    }

}


