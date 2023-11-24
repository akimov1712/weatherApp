package ru.topbun.weather.domain.useCases

import ru.topbun.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class GetWeatherCoordsUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String) = repository.getWeatherCity(city)
}