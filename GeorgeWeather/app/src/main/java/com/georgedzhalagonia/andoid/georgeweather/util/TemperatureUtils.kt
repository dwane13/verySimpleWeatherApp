package com.georgedzhalagonia.andoid.georgeweather.util

import kotlin.math.roundToInt


fun Double.toCelsius() : Double = (this - 273.15).toInt().toDouble()