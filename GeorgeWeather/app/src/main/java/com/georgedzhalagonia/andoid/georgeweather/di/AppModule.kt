package com.georgedzhalagonia.andoid.georgeweather.di

import android.content.Context
import com.georgedzhalagonia.andoid.georgeweather.common.Constants
import com.georgedzhalagonia.andoid.georgeweather.data.remote.OpenWeatherApi
import com.georgedzhalagonia.andoid.georgeweather.data.repository.FusedLocationRepository
import com.georgedzhalagonia.andoid.georgeweather.data.repository.OpenWeatherRepository
import com.georgedzhalagonia.andoid.georgeweather.domain.repository.LocationRepository
import com.georgedzhalagonia.andoid.georgeweather.domain.repository.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(api: OpenWeatherApi): WeatherRepository {
        return OpenWeatherRepository(api)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(locationClient: FusedLocationProviderClient): LocationRepository {
        return FusedLocationRepository(locationClient)
    }

    @Provides
    @Singleton
    fun provideApi(moshi: MoshiConverterFactory, client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(moshi)
        .client(client)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(Constants.BASE_URL)
        .build()
        .create(OpenWeatherApi::class.java)

    @Provides
    @Singleton
    fun provideMoshiFactory() = MoshiConverterFactory.create(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    @Singleton
    @Provides
    fun provideHttpClient() = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build();


    @Provides
    @Singleton
    fun provideLocationClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}