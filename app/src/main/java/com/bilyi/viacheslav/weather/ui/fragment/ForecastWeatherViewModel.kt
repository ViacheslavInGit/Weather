package com.bilyi.viacheslav.weather.ui.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bilyi.viacheslav.weather.data.weather.WeatherApi
import com.bilyi.viacheslav.weather.data.weather.gson.ForecastWeatherResult
import com.bilyi.viacheslav.weather.domain.LatLng
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
            locationRepository.getLatLng()
                .flatMap { latLng: LatLng ->
                    weatherApi.getForecastWeather(
                        latLng.latitude,
                        latLng.longitude,
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