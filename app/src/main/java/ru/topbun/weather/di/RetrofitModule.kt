package ru.topbun.weather.di

import dagger.Module
import dagger.Provides
import ru.topbun.weather.data.networkSource.ApiFactory

@Module
interface RetrofitModule {
    companion object{

        @Provides
        fun provideWeatherApi() = ApiFactory.weatherApi

    }
}