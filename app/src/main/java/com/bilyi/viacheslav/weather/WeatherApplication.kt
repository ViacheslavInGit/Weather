package com.bilyi.viacheslav.weather

import com.bilyi.viacheslav.weather.di.component.DaggerWeatherApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<WeatherApplication> =
        DaggerWeatherApplicationComponent.factory()
            .create(this)
}