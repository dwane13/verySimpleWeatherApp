package com.georgedzhalagonia.andoid.georgeweather.data.repository

import android.annotation.SuppressLint
import com.georgedzhalagonia.andoid.georgeweather.domain.model.UserLocation
import com.georgedzhalagonia.andoid.georgeweather.domain.model.toUserLocation
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FusedLocationRepository @Inject constructor(
    private val locationClient: FusedLocationProviderClient
) : LocationRepository {


    @ExperimentalCoroutinesApi
    @SuppressLint("MissingPermission")
    override suspend fun getUserLocation(): Flow<Operation<UserLocation>> = channelFlow {
        send(Operation.Loading<UserLocation>())

        locationClient.lastLocation.addOnSuccessListener {
            launch {
                send(Operation.Success(it.toUserLocation()))
                close()
            }
        }
        locationClient.lastLocation.addOnFailureListener {
            launch {
                send(
                    Operation.Error<UserLocation>(
                        Exception(),
                        "Cant retrieve device last location."
                    )
                )
                close()
            }
        }

        awaitClose()
    }
}