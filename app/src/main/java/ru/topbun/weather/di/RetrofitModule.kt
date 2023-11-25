package ru.topbun.weather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.topbun.weather.data.sources.networkSource.RetrofitFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RetrofitModule {
    companion object{

        @Provides
        @Singleton
        fun provideWeatherApi() = RetrofitFactory.weatherApi

    }
}