package ru.topbun.weather.data.sources.networkSource

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.topbun.weather.Const.API_KEY
import ru.topbun.weather.data.sources.networkSource.entity.WeatherResponse


interface WeatherApiService {

    @GET("weather/")
    suspend fun getWeather(
        @Query(QUERY_PARAM_CITY) city:String,
        @Query(QUERY_PARAM_APIKEY) apikey: String = API_KEY,
        @Query(QUERY_PARAM_LANG) lang: String = "ru",
    ): Response<WeatherResponse>

    @GET("weather/")
    suspend fun getWeather(
        @Query(QUERY_PARAM_LON) lon:Float,
        @Query(QUERY_PARAM_LAT) lat:Float,
        @Query(QUERY_PARAM_APIKEY) apikey: String = API_KEY,
        @Query(QUERY_PARAM_LANG) lang: String = "ru",
    ): Response<WeatherResponse>

    companion object{
        private const val QUERY_PARAM_APIKEY = "appid"
        private const val QUERY_PARAM_CITY = "q"
        private const val QUERY_PARAM_LON = "lon"
        private const val QUERY_PARAM_LAT = "lat"
        private const val QUERY_PARAM_LANG = "lang"
    }

}