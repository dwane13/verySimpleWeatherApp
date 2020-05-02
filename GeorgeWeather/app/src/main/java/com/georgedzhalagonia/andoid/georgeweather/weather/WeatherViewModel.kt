package com.georgedzhalagonia.andoid.georgeweather.weather

import android.app.Application
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.georgedzhalagonia.andoid.georgeweather.BuildConfig
import com.georgedzhalagonia.andoid.georgeweather.data.UserLocation
import com.georgedzhalagonia.andoid.georgeweather.networking.WeatherApi
import com.georgedzhalagonia.andoid.georgeweather.util.kelvinToCelsius
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

private const val API_KEY = BuildConfig.WEATHER_API

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _place = MutableLiveData<String>()
    val place : LiveData<String>
        get() = _place

    private val _temperature = MutableLiveData<String>()
    val temperature : LiveData<String>
        get() = _temperature

    private val _description = MutableLiveData<String>()
    val description : LiveData<String>
        get() = _description

    private val _locationPermission = MutableLiveData<Boolean>()

    fun onPermissionResult(granted: Boolean) {
        _locationPermission.value = granted
        getUserLocation()
    }

    private fun getWeatherData(userLocation: UserLocation) {
        coroutineScope.launch {
            try {
                val getWeatherAsync = WeatherApi.retrofitService.getWeatherDataAsync(
                        userLocation.latitude,
                        userLocation.longitude,
                        API_KEY).await()
                _place.value = getWeatherAsync.city
                _description.value = getWeatherAsync.weather[0].description
                _temperature.value = kelvinToCelsius(getWeatherAsync.main.temp).toString()
            } catch (t: Exception) {
                Log.e("ERROR", "while fetching API data", t)
            }
        }
    }

    private fun getUserLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context.applicationContext)

        fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    getWeatherData(UserLocation(location!!.longitude, location.latitude))
                }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}