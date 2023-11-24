package ru.topbun.weather.data.networkSource

import retrofit2.http.GET
import retrofit2.http.Query
import ru.topbun.weather.Const.API_KEY
import ru.topbun.weather.data.networkSource.entity.WeatherResponse


interface WeatherApiService {

    @GET("weather/")
    suspend fun getWeatherCity(
        @Query(QUERY_PARAM_CITY) city:String,
        @Query(QUERY_PARAM_APIKEY) apikey: String = API_KEY
    ): WeatherResponse

    @GET("weather/")
    suspend fun getWeatherCoords(
        @Query(QUERY_PARAM_LON) lon:Float,
        @Query(QUERY_PARAM_LAT) lat:Float,
        @Query(QUERY_PARAM_APIKEY) apikey: String = API_KEY
    ): WeatherResponse

    companion object{
        private const val QUERY_PARAM_APIKEY = "appid"
        private const val QUERY_PARAM_CITY = "q"
        private const val QUERY_PARAM_LON = "lon"
        private const val QUERY_PARAM_LAT = "lat"
    }

}