package com.georgedzhalagonia.andoid.georgeweather.domain.usecases

import android.location.Location
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    operator fun invoke(): Flow<Operation<Location>> = flow {
        locationRepository.getUserLocation().collectLatest {
            emit(it)
        }
    }
}