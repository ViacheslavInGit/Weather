package com.bilyi.viacheslav.weather.data.weather

import com.bilyi.viacheslav.weather.data.weather.gson.ForecastWeatherResult
import com.bilyi.viacheslav.weather.data.weather.gson.WeatherResult
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String

    ): Observable<WeatherResult>

    @GET("forecast")
    fun getForecastWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String

    ): Observable<ForecastWeatherResult>
}