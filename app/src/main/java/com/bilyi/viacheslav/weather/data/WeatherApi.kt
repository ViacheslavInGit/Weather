package com.bilyi.viacheslav.weather.data

import com.bilyi.viacheslav.weather.data.gson.WeatherResult
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeatherByLatLng(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String

    ): Single<WeatherResult>

    @GET("weather")
    fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("APPID") apiKey: String
    ): Single<WeatherResult>
}