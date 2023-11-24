package ru.topbun.weather.domain.entity

data class Temperature (
    val temp: Int,
    val feelsTemp: Int,
    val pressure: Int,
    val humidity: Int,
    val tempMin: Int,
    val tempMax: Int,
)