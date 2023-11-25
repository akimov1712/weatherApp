package ru.topbun.weather.data.sources.networkSource.entity

data class SysDto(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)