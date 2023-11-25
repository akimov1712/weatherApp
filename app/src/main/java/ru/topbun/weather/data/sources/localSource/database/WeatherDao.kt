package ru.topbun.weather.data.sources.localSource.database

import androidx.room.Dao
import androidx.room.Update
import retrofit2.http.GET
import ru.topbun.weather.data.sources.localSource.database.entity.WeatherDbEntity

@Dao
interface WeatherDao {

    @GET("SELECT * FROM weather_now")
    fun getWeather(): WeatherDbEntity

    @Update
    fun updateWeather(weather: WeatherDbEntity)

}