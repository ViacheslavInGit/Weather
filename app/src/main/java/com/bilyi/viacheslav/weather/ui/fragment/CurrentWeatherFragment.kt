package com.bilyi.viacheslav.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bilyi.viacheslav.weather.R
import com.bilyi.viacheslav.weather.data.gson.WeatherResult
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_current_weather.*
import javax.inject.Inject

class CurrentWeatherFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[CurrentWeatherViewModel::class.java]

        viewModel.weatherLiveData.observe(this, Observer { weatherResult ->
            showWeather(weatherResult)
        })
    }

    private fun showWeather(weather: WeatherResult) {
        celsiusTextView.text =
            getString(R.string.celsius_pattern, weather.currentCelsius.toString())

        descriptionTextView.text = weather.description

        currentWeatherRoot.setBackgroundColor(
            getSuitableColor(weather.currentCelsius)
        )
    }

    private fun getSuitableColor(celsius: Int): Int {
        return resources.getColor(
            when {
                celsius > 30 -> R.color.hotWeather
                celsius > 20 -> R.color.heatWeather
                celsius > 8 -> R.color.averageWeather
                celsius > -5 -> R.color.coolWeather
                else -> R.color.frostWeather
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weatherLiveData.value?.let { showWeather(it) }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateWeather()
    }

}