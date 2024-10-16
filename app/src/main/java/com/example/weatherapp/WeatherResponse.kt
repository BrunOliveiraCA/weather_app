package com.example.weatherapp


import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") weatherApiKey: String = BuildConfig.WEATHER_API_KEY
    ): WeatherResponse
}