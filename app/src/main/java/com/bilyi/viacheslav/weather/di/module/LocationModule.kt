package com.bilyi.viacheslav.weather.di.module

import android.content.Context
import com.bilyi.viacheslav.weather.data.LocationRepositoryImpl
import com.bilyi.viacheslav.weather.domain.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun provideLocationRepo(repoImpl: LocationRepositoryImpl): LocationRepository

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideLocationClient(context: Context): FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
    }
}