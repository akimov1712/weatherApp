package ru.topbun.weather.data.localSource.database.entity

import androidx.room.Entity

@Entity(tableName = "weather_now")
data class WeatherDbEntity(
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
    val speed: Int,
    val deg: Int,
    val cloudPercent: Int,
    val sunrise:Long,
    val sunset:Long,
)
