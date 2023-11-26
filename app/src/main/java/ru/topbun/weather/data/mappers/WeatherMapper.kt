package ru.topbun.weather.data.mappers

import ru.topbun.weather.data.sources.localSource.database.entity.WeatherDbEntity
import ru.topbun.weather.data.sources.networkSource.entity.WeatherResponse
import ru.topbun.weather.domain.entity.InfoWeather
import ru.topbun.weather.domain.entity.Temperature
import ru.topbun.weather.domain.entity.WeatherEntity
import ru.topbun.weather.domain.entity.Wind
import ru.topbun.weather.utils.secToMillis
import ru.topbun.weather.utils.toCelsius
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun mapResponseToDbEntity(response: WeatherResponse) =
        WeatherDbEntity(
            name = response.name,
            timeUpdate = response.timeStamp.secToMillis(),
            titleWeather = response.weather.first().description,
            weatherCode = response.weather.first().id,
            icon = response.weather.first().icon,
            temp = response.main.temp.toInt().toCelsius(),
            feelsTemp = response.main.feels_like.toInt().toCelsius(),
            pressure = response.main.pressure,
            humidity = response.main.humidity,
            tempMin = response.main.temp_min.toInt().toCelsius(),
            tempMax = response.main.temp_max.toInt().toCelsius(),
            rangeVisibility = response.visibility,
            speedWind = response.wind.speed,
            deg = response.wind.deg,
            cloudPercent = response.clouds.all,
            sunrise = response.sys.sunrise.toLong().secToMillis(),
            sunset = response.sys.sunset.toLong().secToMillis()
        )

    fun mapDbEntityToEntity(dbEntity: WeatherDbEntity) =
        WeatherEntity(
            infoWeather = InfoWeather(
                name = dbEntity.name,
                timeUpdate = dbEntity.timeUpdate,
                titleWeather = dbEntity.titleWeather,
                weatherCode = dbEntity.weatherCode,
                icon = dbEntity.icon
            ),
            temperature = Temperature(
                temp = dbEntity.temp,
                feelsTemp = dbEntity.feelsTemp,
                pressure = dbEntity.pressure,
                humidity = dbEntity.humidity,
                tempMax = dbEntity.tempMax,
                tempMin = dbEntity.tempMin
            ),
            rangeVisibility = dbEntity.rangeVisibility,
            wind = Wind(
                speedWind = dbEntity.speedWind,
                deg = dbEntity.deg
            ),
            cloudPercent = dbEntity.cloudPercent,
            sunrise = dbEntity.sunrise,
            sunset = dbEntity.sunset,
            dataRequestTime = dbEntity.dataRequestTime
        )

}