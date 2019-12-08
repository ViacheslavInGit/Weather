package com.bilyi.viacheslav.weather.di.module

import com.bilyi.viacheslav.weather.ui.MainActivity
import com.bilyi.viacheslav.weather.di.scope.ActivityScope
import com.bilyi.viacheslav.weather.di.scope.FragmentScope
import com.bilyi.viacheslav.weather.ui.fragment.CurrentWeatherFragment
import com.bilyi.viacheslav.weather.ui.fragment.ForecastWeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {


    // генерирует AndroidInjector для Activity/Fragment
    @ContributesAndroidInjector
    @ActivityScope
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    @FragmentScope
    fun contributeCurrentWeatherFragment(): CurrentWeatherFragment

    @ContributesAndroidInjector
    @FragmentScope
    fun contributeForecastWeatherFragment(): ForecastWeatherFragment
}