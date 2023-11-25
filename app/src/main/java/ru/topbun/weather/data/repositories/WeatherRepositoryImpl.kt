package ru.topbun.weather.data.repositories

import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import ru.topbun.weather.data.ClientErrorException
import ru.topbun.weather.data.ConnectException
import ru.topbun.weather.data.ServerErrorException
import ru.topbun.weather.data.sources.localSource.database.WeatherDao
import ru.topbun.weather.data.sources.networkSource.WeatherApiService
import ru.topbun.weather.domain.entity.WeatherEntity
import ru.topbun.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApiService
): WeatherRepository {

    override suspend fun getWeatherCity(city: String): Flow<WeatherEntity> {
        try {
            val weatherResponse = weatherApi.getWeather(city)
            when(weatherResponse.code()){
                in 200..299 -> {
                    val response = weatherResponse.body()
                    response
                }
                in 400..499 -> throw ClientErrorException()
                in 500..599 -> throw ServerErrorException()
            }
        } catch (e: HttpException){
            throw ConnectException()
        }
        return TODO()
    }

    override suspend fun getWeatherCoords(lon: Float, lat: Float): Flow<WeatherEntity> {
        TODO("Not yet implemented")
    }
}