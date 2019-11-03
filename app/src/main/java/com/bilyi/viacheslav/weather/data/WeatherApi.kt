package com.bilyi.viacheslav.weather.data

import com.bilyi.viacheslav.weather.data.gson.WeatherResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String

    ): Single<WeatherResult>

    @GET("forecast/hourly")
    fun getForecastWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String

    ): Single<WeatherResult>
}