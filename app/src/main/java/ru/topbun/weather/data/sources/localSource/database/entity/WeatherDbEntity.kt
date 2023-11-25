package ru.topbun.weather.data.sources.localSource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.topbun.weather.Const.WEATHER_NOW_KEY

@Entity(tableName = "weather_now")
data class WeatherDbEntity(
    @PrimaryKey
    val id: Int = WEATHER_NOW_KEY,
    val name: String,
    val timeUpdate: Long,
    val titleWeather: String,
    val weatherCode: Int,
    val icon: String,
    val temp: Int,
    val feelsTemp: Int,
    val pressure: Int,
    val humidity: Int,
    val tempMin: Int,
    val tempMax: Int,
    val rangeVisibility: Int,
    val speedWind: Double,
    val deg: Int,
    val cloudPercent: Int,
    val sunrise:Long,
    val sunset:Long,
)
