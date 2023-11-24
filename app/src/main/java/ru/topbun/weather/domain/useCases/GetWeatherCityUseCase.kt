package ru.topbun.weather.domain.useCases

import ru.topbun.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class GetWeatherCityUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lon: Float, lat: Float) = repository.getWeatherCoords(lon, lat)
}