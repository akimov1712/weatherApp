package ru.topbun.weather

object Const {

    const val API_KEY = "e1488ee2650e634b2063b23202142aab"
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    const val WEATHER_NOW_KEY = 1

    val mapIcon = mapOf(
        "01d" to R.drawable.icon_clean_d,
        "01n" to R.drawable.icon_clean_n,
        "02d" to R.drawable.icon_low_cloud_d,
        "02n" to R.drawable.icon_low_cloud_n,
        "03d" to R.drawable.icon_cloud,
        "03n" to R.drawable.icon_cloud,
        "04d" to R.drawable.icon_heavy_cloud,
        "04n" to R.drawable.icon_heavy_cloud,
        "09d" to R.drawable.icon_snowrain,
        "09n" to R.drawable.icon_snowrain,
        "10d" to R.drawable.icon_rain_d,
        "10n" to R.drawable.icon_rain_n,
        "11d" to R.drawable.icon_thunder,
        "11n" to R.drawable.icon_thunder,
        "13d" to R.drawable.icon_snow,
        "13n" to R.drawable.icon_snow,
        "50d" to R.drawable.icon_fog,
        "50n" to R.drawable.icon_fog,
    )

}