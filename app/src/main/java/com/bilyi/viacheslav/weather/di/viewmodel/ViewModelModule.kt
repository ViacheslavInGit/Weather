package com.bilyi.viacheslav.weather.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bilyi.viacheslav.weather.ui.MainActivityViewModel
import com.bilyi.viacheslav.weather.ui.fragment.CurrentWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    fun currentWeatherViewModel(viewModel: CurrentWeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun currentMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}