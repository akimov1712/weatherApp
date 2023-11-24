package ru.topbun.weather.di.entity

data class WeatherEntity(
    val infoWeather: InfoWeather,
    val temperature: Temperature,
    val rangeVisibility: Int,
    val wind: Wind,
    val cloudPercent: Int,
    val sunrise:Long,
    val sunset:Long,
)
