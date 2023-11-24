package ru.topbun.weather.data.networkSource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.topbun.weather.Const.BASE_URL

object ApiFactory {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val weatherApi = retrofit.create(WeatherApiService::class.java)

}