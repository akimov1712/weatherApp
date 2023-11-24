package ru.topbun.weather.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.topbun.weather.domain.entity.WeatherEntity

interface WeatherRepository {

    fun getWeatherCity(city: String): Flow<WeatherEntity>
    fun getWeatherCoords(lon: Float, lat: Float): Flow<WeatherEntity>

}