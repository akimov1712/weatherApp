package ru.topbun.weather.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.topbun.weather.domain.entity.WeatherEntity

interface WeatherRepository {

    suspend fun getWeatherCity(city: String): Flow<WeatherEntity>
    suspend fun getWeatherCoords(lon: Float, lat: Float): Flow<WeatherEntity>
    suspend fun getCachedWeather(): Flow<WeatherEntity>

}