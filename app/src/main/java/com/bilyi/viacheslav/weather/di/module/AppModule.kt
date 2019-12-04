package com.bilyi.viacheslav.weather.di.module

import android.content.Context
import com.bilyi.viacheslav.weather.WeatherApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideContext(application: WeatherApplication): Context {
            return application.applicationContext
        }
    }

}