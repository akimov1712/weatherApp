package ru.topbun.weather.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import ru.topbun.weather.data.CachedDataException
import ru.topbun.weather.data.mappers.WeatherMapper
import ru.topbun.weather.data.sources.localSource.database.WeatherDao
import ru.topbun.weather.data.sources.networkSource.BaseRetrofitSource
import ru.topbun.weather.data.sources.networkSource.WeatherApiService
import ru.topbun.weather.data.sources.networkSource.entity.WeatherResponse
import ru.topbun.weather.domain.entity.WeatherEntity
import ru.topbun.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApiService,
    private val weatherMapper: WeatherMapper
) : WeatherRepository, BaseRetrofitSource() {

    override suspend fun getCachedWeather(): Flow<WeatherEntity> {
        return weatherDao.getWeather().map {
            if (it == null) {
               throw CachedDataException()
            }
            weatherMapper.mapDbEntityToEntity(it)
        }
    }

    override suspend fun getWeatherCity(city: String): Flow<WeatherEntity> =
        wrapRetrofitExceptions {
            val weatherResponse = weatherApi.getWeather(city)
            returnDataFlow(weatherResponse)
        }


    private suspend fun returnDataFlow(weatherResponse: Response<WeatherResponse>): Flow<WeatherEntity> {
        processData(weatherResponse)
        return getCachedWeather()
    }

    private suspend fun processData(weatherResponse: Response<WeatherResponse>) {
        if (weatherResponse.isSuccessful) {
            weatherResponse.body()?.let {
                weatherDao.insertWeather(
                    weatherMapper.mapResponseToDbEntity(it)
                )
            }
        }
    }

    override suspend fun getWeatherCoords(lon: Float, lat: Float): Flow<WeatherEntity> =
        wrapRetrofitExceptions {
            val weatherResponse = weatherApi.getWeather(lon, lat)
            returnDataFlow(weatherResponse)
        }
}