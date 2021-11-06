package com.georgedzhalagonia.andoid.georgeweather.domain.usecases

import com.georgedzhalagonia.andoid.georgeweather.domain.repository.WeatherRepository
import javax.inject.Inject
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Operation
import com.georgedzhalagonia.andoid.georgeweather.domain.model.utility.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody


class GetWeatherIconUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(iconId: String): Flow<Operation<Bitmap>> = flow {
        emit(Operation.Loading<Bitmap>())
        val result = repository.getWeatherIcon(iconId)

        if (result is Response.Success<ResponseBody>) {
            val bmp = BitmapFactory.decodeStream(result.data.byteStream())
            emit(Operation.Success(bmp))
        } else if (result is Response.Error) {
            emit(Operation.Error(result.e, result.msg))
        }
    }
}