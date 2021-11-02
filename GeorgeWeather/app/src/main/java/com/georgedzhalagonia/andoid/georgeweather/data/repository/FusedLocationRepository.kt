package com.georgedzhalagonia.andoid.georgeweather.data.repository

import android.location.Location
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FusedLocationRepository @Inject constructor(
    private val locationClient: FusedLocationProviderClient
): LocationRepository {


    override suspend fun getUserLocation(): Flow<Operation<Location>> = flow {
        emit(Operation.Loading())
        try {
            locationClient.lastLocation.addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Operation.Success(it))
                }
            }
        } catch (e: SecurityException) {
            emit(Operation.Error(e, "App needs location permission"))
        } as Unit
    }
}