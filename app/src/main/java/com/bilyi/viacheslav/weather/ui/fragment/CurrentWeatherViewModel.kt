package com.bilyi.viacheslav.weather.ui.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bilyi.viacheslav.weather.data.WeatherApi
import com.bilyi.viacheslav.weather.data.gson.WeatherResult
import com.bilyi.viacheslav.weather.di.qualifier.WeatherApiKey
import com.bilyi.viacheslav.weather.ui.base.BaseViewModel
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val weatherApi: WeatherApi,
    @WeatherApiKey private val apiKey: String
) : BaseViewModel() {

    val weatherLiveData = MutableLiveData<WeatherResult>()

    fun updateWeather() {
        subscribe(
            weatherApi.getWeather(
                latitude,
                longitude,
                apiKey
            ),
            { weatherLiveData.value = it },
            { throwable -> Log.e("", "", throwable) }
        )

    }

    companion object {
        const val latitude = 55.01
        const val longitude = 36.22
    }
}