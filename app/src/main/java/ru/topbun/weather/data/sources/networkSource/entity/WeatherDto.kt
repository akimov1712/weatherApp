package ru.topbun.weather.data.sources.networkSource.entity

data class WeatherDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)