package ru.topbun.weather.data.sources.localSource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.topbun.weather.Const.WEATHER_NOW_KEY
import ru.topbun.weather.data.sources.localSource.database.entity.WeatherDbEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_now WHERE id = :keyId")
    fun getWeather(keyId: Int = WEATHER_NOW_KEY): Flow<WeatherDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWeather(weather: WeatherDbEntity)

}