package com.bilyi.viacheslav.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bilyi.viacheslav.weather.R
import com.bilyi.viacheslav.weather.data.weather.gson.WeatherResult
import com.bilyi.viacheslav.weather.util.getSuitableColor
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_current_weather.*
import javax.inject.Inject

class CurrentWeatherFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelFactory.create(CurrentWeatherViewModel::class.java)

        viewModel.weatherLiveData.observe(this, Observer { weatherResult ->
            showWeather(weatherResult)
        })
    }

    private fun showWeather(weather: WeatherResult) {
        celsiusTextView.text =
            getString(R.string.celsius_pattern, weather.currentCelsius.toString())

        descriptionTextView.text = weather.description

        currentWeatherRoot.setBackgroundColor(
            resources.getColor(
                weather.currentCelsius.getSuitableColor(),
                requireActivity().theme
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateWeather()
    }

}