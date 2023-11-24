package ru.topbun.weather.data.networkSource.entity

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("id")
    val idCity: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: CloudsDto,
    @SerializedName("cod")
    val codeResponse: Int,
    @SerializedName("coord")
    val coords: CoordDto,
    @SerializedName("dt")
    val timeStamp: Int,
    @SerializedName("main")
    val main: MainDto,
    @SerializedName("snow")
    val snow: SnowDto,
    @SerializedName("sys")
    val sys: SysDto,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<WeatherDto>,
    @SerializedName("wind")
    val wind: WindDto
)