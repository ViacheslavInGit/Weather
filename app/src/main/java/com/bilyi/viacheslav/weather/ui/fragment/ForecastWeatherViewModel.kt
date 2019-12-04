package com.bilyi.viacheslav.weather.ui.fragment

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bilyi.viacheslav.weather.data.WeatherApi
import com.bilyi.viacheslav.weather.data.gson.ForecastWeatherResult
import com.bilyi.viacheslav.weather.di.qualifier.WeatherApiKey
import com.bilyi.viacheslav.weather.domain.LocationRepository
import com.bilyi.viacheslav.weather.ui.base.BaseViewModel
import javax.inject.Inject

class ForecastWeatherViewModel @Inject constructor(
    private val weatherApi: WeatherApi,
    @WeatherApiKey private val apiKey: String,
    private val locationRepository: LocationRepository
) : BaseViewModel() {

    val forecastWeatherLiveData = MutableLiveData<ForecastWeatherResult>()

    fun updateForecastWeather() {
        subscribe(
            locationRepository.getLocation()
                .flatMap { location: Location ->
                    weatherApi.getForecastWeather(
                        location.latitude,
                        location.longitude,
                        apiKey
                    )
                },
            {
                forecastWeatherLiveData.value = it
            },
            { throwable -> Log.e("###", "", throwable) }
        )
    }
}