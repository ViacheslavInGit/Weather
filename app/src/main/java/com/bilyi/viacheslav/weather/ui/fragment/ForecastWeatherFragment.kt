package com.bilyi.viacheslav.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bilyi.viacheslav.weather.R
import dagger.android.support.DaggerFragment

class ForecastWeatherFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast_weather, container, false)
    }


}