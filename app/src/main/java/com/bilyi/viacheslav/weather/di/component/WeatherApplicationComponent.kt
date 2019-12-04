package com.bilyi.viacheslav.weather.di.component

import com.bilyi.viacheslav.weather.WeatherApplication
import com.bilyi.viacheslav.weather.di.module.ActivityModule
import com.bilyi.viacheslav.weather.di.module.AppModule
import com.bilyi.viacheslav.weather.di.module.LocationModule
import com.bilyi.viacheslav.weather.di.module.NetworkModule
import com.bilyi.viacheslav.weather.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,

        AppModule::class,
        NetworkModule::class,
        LocationModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface WeatherApplicationComponent : AndroidInjector<WeatherApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<WeatherApplication>

}