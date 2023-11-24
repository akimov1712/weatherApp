package ru.topbun.weather.di.useCases

import ru.topbun.weather.di.repositories.WeatherRepository
import javax.inject.Inject

class GetWeatherCoordsUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String) = repository.getWeatherCity(city)
}