package com.bilyi.viacheslav.weather

// сгенерированная реализация компонента
import com.bilyi.viacheslav.weather.di.component.DaggerWeatherApplicationComponent

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

// Application, нужен что бы "запустить" даггер
class WeatherApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<WeatherApplication> =
        DaggerWeatherApplicationComponent.factory()
            .create(this)
}