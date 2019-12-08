package com.bilyi.viacheslav.weather.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bilyi.viacheslav.weather.ui.MainActivityViewModel
import com.bilyi.viacheslav.weather.ui.fragment.CurrentWeatherViewModel
import com.bilyi.viacheslav.weather.ui.fragment.ForecastWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ViewModelModule {

    // фабрика получит мапу, созданную @IntoMap и содержащую все вьюмодели.
    // Фабрика по классу просто возращает нужный обьект из мапы
    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // провайдит ViewModel используя CurrentWeatherViewModel
    // "кладет" результат в мапу, где ключ - класс (обьект - значение)
    // Singleton для того что бы не пересоздавалась и зранила последние данные
    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    @Singleton
    fun currentWeatherViewModel(viewModel: CurrentWeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForecastWeatherViewModel::class)
    @Singleton
    fun forecastWeatherViewModel(viewModel: ForecastWeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    @Singleton
    fun currentMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}