package ru.topbun.weather.data.networkSource.entity

data class SysDto(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)