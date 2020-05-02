package com.georgedzhalagonia.andoid.georgeweather.util

import kotlin.math.roundToInt


fun kelvinToCelsius(kelvin: Double) : Int = (kelvin - 273.15).roundToInt()