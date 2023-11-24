package ru.topbun.weather.di.entity

data class InfoWeather(
    val name: String,
    val timeUpdate: Long,
    val titleWeather: String,
    val weatherCode: Int,
    val icon: String,
)
