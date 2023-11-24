package ru.topbun.weather.data.networkSource.entity

data class WeatherDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)