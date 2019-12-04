package com.bilyi.viacheslav.weather.ui.fragment

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bilyi.viacheslav.weather.data.WeatherApi
import com.bilyi.viacheslav.weather.data.gson.WeatherResult
import com.bilyi.viacheslav.weather.di.qualifier.WeatherApiKey
import com.bilyi.viacheslav.weather.domain.LocationRepository
import com.bilyi.viacheslav.weather.ui.base.BaseViewModel
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val weatherApi: WeatherApi,
    @WeatherApiKey private val apiKey: String,
    private val locationRepository: LocationRepository
) : BaseViewModel() {

    val weatherLiveData = MutableLiveData<WeatherResult>()

    fun updateWeather() {

        subscribe(
            locationRepository.getLocation()
                .flatMap { location: Location ->
                    weatherApi.getWeather(
                        location.latitude,
                        location.longitude,
                        apiKey
                    )
                },
            {
                weatherLiveData.value = it
            },
            { throwable -> Log.e("###", "", throwable) }
        )
    }
}