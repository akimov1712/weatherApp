package ru.topbun.weather.di.repositories

import kotlinx.coroutines.flow.Flow
import ru.topbun.weather.di.entity.WeatherEntity

interface WeatherRepository {

    fun getWeatherCity(city: String): Flow<WeatherEntity>
    fun getWeatherCoords(lon: Float, lat: Float): Flow<WeatherEntity>

}