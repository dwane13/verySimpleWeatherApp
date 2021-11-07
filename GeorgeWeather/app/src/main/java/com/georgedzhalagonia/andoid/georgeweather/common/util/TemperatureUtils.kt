package com.georgedzhalagonia.andoid.georgeweather.common.util


fun Double.toCelsius() : Double = (this - 273.15).toInt().toDouble()