package ru.topbun.weather.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.topbun.weather.data.localSource.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RoomModule {
    companion object{

        @Provides
        @Singleton
        fun provideWeatherDao(application: Application) =
            AppDatabase.getInstance(application).weatherDao()


    }
}