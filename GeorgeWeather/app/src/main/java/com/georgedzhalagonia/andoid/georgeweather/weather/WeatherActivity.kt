package com.georgedzhalagonia.andoid.georgeweather.weather

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.georgedzhalagonia.andoid.georgeweather.R
import com.georgedzhalagonia.andoid.georgeweather.databinding.WeatherActivityBinding
import com.google.android.gms.location.LocationServices

private const val USER_LOCATION_PERMISSION = 0

/**
 * @author george dzhalagoniya
 * @version 0.0.2
 */
class WeatherActivity : AppCompatActivity() {

    lateinit var viewModelFactory : WeatherViewModelFactory
    lateinit var viewModel : WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: WeatherActivityBinding = DataBindingUtil.setContentView(this, R.layout.weather_activity)

        val application = requireNotNull(this).application
        val viewModelFactory = WeatherViewModelFactory(application)

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    Log.d("DEBUG", "got location in activity")
                }

        val viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)

        binding.weatherActivityViewModel = viewModel
        binding.lifecycleOwner = this

        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                    USER_LOCATION_PERMISSION)
        } else {
            viewModel.onPermissionResult(true)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            USER_LOCATION_PERMISSION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    viewModel.onPermissionResult(true)
                } else {
                    viewModel.onPermissionResult(false)
                }
            }
        }
    }
}