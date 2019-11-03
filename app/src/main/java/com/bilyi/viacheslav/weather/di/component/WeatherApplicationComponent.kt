package com.bilyi.viacheslav.weather.di.component

import com.bilyi.viacheslav.weather.WeatherApplication
import com.bilyi.viacheslav.weather.di.module.ActivityModule
import com.bilyi.viacheslav.weather.di.module.DataModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,

        DataModule::class,
        ActivityModule::class
    ]
)
@Singleton
interface WeatherApplicationComponent : AndroidInjector<WeatherApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<WeatherApplication>

}