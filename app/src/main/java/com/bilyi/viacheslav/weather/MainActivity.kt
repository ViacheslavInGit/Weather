package com.bilyi.viacheslav.weather

import android.os.Bundle
import android.util.Log
import com.bilyi.viacheslav.weather.data.WeatherApi
import dagger.android.DaggerActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerActivity() {

    @Inject
    lateinit var weatherApi: WeatherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val d = weatherApi.getWeatherByLatLng(
            latitude = 55.0,
            longitude = 37.5,
            apiKey = getString(R.string.weather_api_key)
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    celsiusTextView.text = it.celsiusMax.toString()
                    descriptionTextView.text = it.description
                },
                { Log.e("(MainActivity.kt:28)", it.toString(), it) }
            )

    }
}
