package ru.topbun.weather.presentation.weather

import ru.topbun.weather.domain.entity.WeatherEntity

sealed class WeatherState {

    data class WeatherItem(val weather: WeatherEntity): WeatherState()

    data object Loading: WeatherState()

    data object ConnectError: WeatherState()
    data object ParseBackendResponseError: WeatherState()
    data class BackendError(val message: String) : WeatherState()
    data object CachedDataError: WeatherState()

}