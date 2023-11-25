package ru.topbun.weather.di

import dagger.Module
import dagger.Provides
import ru.topbun.weather.data.sources.networkSource.RetrofitFactory

@Module
interface RetrofitModule {
    companion object{

        @Provides
        fun provideWeatherApi() = RetrofitFactory.weatherApi

    }
}