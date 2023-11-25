package ru.topbun.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.topbun.weather.data.repositories.WeatherRepositoryImpl
import ru.topbun.weather.domain.repositories.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

}