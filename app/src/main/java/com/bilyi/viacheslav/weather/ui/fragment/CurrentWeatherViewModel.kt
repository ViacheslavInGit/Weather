package com.bilyi.viacheslav.weather.ui.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bilyi.viacheslav.weather.data.weather.WeatherApi
import com.bilyi.viacheslav.weather.data.weather.gson.WeatherResult
import com.bilyi.viacheslav.weather.domain.LatLng
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
            locationRepository.getLatLng()
                .flatMap { latLng: LatLng ->
                    weatherApi.getWeather(
                        latLng.latitude,
                        latLng.longitude,
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