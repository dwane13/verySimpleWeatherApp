package com.georgedzhalagonia.andoid.georgeweather.domain.repository

import android.location.Location
import com.georgedzhalagonia.andoid.georgeweather.domain.model.UserLocation
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun getUserLocation(): Flow<Operation<UserLocation>>
}