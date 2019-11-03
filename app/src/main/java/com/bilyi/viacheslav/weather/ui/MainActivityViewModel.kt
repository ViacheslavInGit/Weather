package com.bilyi.viacheslav.weather.ui

import androidx.lifecycle.MutableLiveData
import com.bilyi.viacheslav.weather.ui.base.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : BaseViewModel() {

    val currentFragmentLiveData = MutableLiveData<WeatherScreen>()
        .apply { value = CurrentWeatherScreen }


    fun invertScreen() {
        when (currentFragmentLiveData.value) {
            CurrentWeatherScreen -> ForecastWeatherScreen
            else -> CurrentWeatherScreen
        }.also {
            currentFragmentLiveData.value = it
        }
    }
}