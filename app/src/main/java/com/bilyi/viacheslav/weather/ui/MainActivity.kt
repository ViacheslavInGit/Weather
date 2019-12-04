package com.bilyi.viacheslav.weather.ui

import android.Manifest
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bilyi.viacheslav.weather.R
import com.bilyi.viacheslav.weather.ui.fragment.CurrentWeatherFragment
import com.bilyi.viacheslav.weather.ui.fragment.ForecastWeatherFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    private val currentWeatherFragment = CurrentWeatherFragment()
    private val forecastWeatherFragment = ForecastWeatherFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    CurrentWeatherFragment()
                )
                .commit()
        }

        viewModel.currentFragmentLiveData.observe(this, Observer { screen ->
            when (screen) {
                CurrentWeatherScreen -> replaceFragment(currentWeatherFragment)
                else -> replaceFragment(forecastWeatherFragment)
            }
        })

        invertScreenButton.setOnClickListener { viewModel.invertScreen() }

        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                fragment
            )
            .commit()
    }

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}

sealed class WeatherScreen
object CurrentWeatherScreen : WeatherScreen()
object ForecastWeatherScreen : WeatherScreen()