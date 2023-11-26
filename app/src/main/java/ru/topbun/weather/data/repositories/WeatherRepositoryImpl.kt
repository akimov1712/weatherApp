package ru.topbun.weather.data.repositories

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import retrofit2.Response
import ru.topbun.weather.data.CachedDataException
import ru.topbun.weather.data.ClientErrorException
import ru.topbun.weather.data.ConnectException
import ru.topbun.weather.data.ServerErrorException
import ru.topbun.weather.data.mappers.WeatherMapper
import ru.topbun.weather.data.sources.localSource.database.WeatherDao
import ru.topbun.weather.data.sources.networkSource.WeatherApiService
import ru.topbun.weather.data.sources.networkSource.entity.WeatherResponse
import ru.topbun.weather.domain.entity.WeatherEntity
import ru.topbun.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApiService,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {

    override suspend fun getCachedWeather(): Flow<WeatherEntity> {
        return weatherDao.getWeather().map {
            it ?: throw CachedDataException()
            weatherMapper.mapDbEntityToEntity(it)
        }
    }

    override suspend fun getWeatherCity(city: String): Flow<WeatherEntity> {
        try {
            val weatherResponse = weatherApi.getWeather(city)
            return returnDataFlow(weatherResponse)
        } catch (e: Exception) {
            throw ConnectException()
        }
    }

    private suspend fun returnDataFlow(weatherResponse: Response<WeatherResponse>): Flow<WeatherEntity> {
        return if (processData(weatherResponse)) weatherDao.getWeather().map {
            weatherMapper.mapDbEntityToEntity(it)
        }
        else flow { }
    }

    private suspend fun processData(weatherResponse: Response<WeatherResponse>): Boolean {
        when (weatherResponse.code()) {
            in 200..299 -> {
                weatherResponse.body()?.let {
                    weatherDao.insertWeather(
                        weatherMapper.mapResponseToDbEntity(it)
                    )
                }
            }

            in 400..499 -> throw ClientErrorException()
            in 500..599 -> throw ServerErrorException()
        }
        return true
    }

    override suspend fun getWeatherCoords(lon: Float, lat: Float): Flow<WeatherEntity> {
        try {
            val weatherResponse = weatherApi.getWeather(lon, lat)
            return returnDataFlow(weatherResponse)
        } catch (e: HttpException) {
            throw ConnectException()
        }
    }
}