package com.bilyi.viacheslav.weather.di.module

import android.content.Context
import com.bilyi.viacheslav.weather.data.location.LocationRepositoryImpl
import com.bilyi.viacheslav.weather.domain.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class LocationModule {

    // провайдит LocationRepository используя LocationRepositoryImpl.
    // что бы @Binds работал - у реализации должен быть @Inject-конструктор
    @Binds
    @Singleton
    abstract fun provideLocationRepo(repoImpl: LocationRepositoryImpl): LocationRepository

    // companion object + @JvmStatic потому что поле у модуль-класса должно быть либо абстрактным либо статическим
    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideLocationClient(context: Context): FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
    }
}