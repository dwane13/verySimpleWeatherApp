package com.georgedzhalagonia.andoid.georgeweather.networking

import com.georgedzhalagonia.andoid.georgeweather.data.WeatherData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/"

val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


val  logging =  HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


val httpClient = OkHttpClient.Builder().apply { addInterceptor(logging) }


val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(httpClient.build())
        .build()

interface WeatherApiService{
    @GET("data/2.5/weather")
    fun getWeatherDataAsync(
            @Query("lat") lat: Double,
            @Query("lon") long: Double,
            @Query("appid") apiKey: String) : Deferred<WeatherData>
}


object WeatherApi {
    val retrofitService : WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}