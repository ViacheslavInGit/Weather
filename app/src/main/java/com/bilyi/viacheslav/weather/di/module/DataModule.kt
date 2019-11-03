package com.bilyi.viacheslav.weather.di.module

import com.bilyi.viacheslav.weather.data.WeatherApi
import com.bilyi.viacheslav.weather.data.gson.WeatherResult
import com.bilyi.viacheslav.weather.data.gson.WeatherResultDeserializer
import com.bilyi.viacheslav.weather.di.qualifier.WeatherApiKey
import com.bilyi.viacheslav.weather.di.qualifier.WeatherApiUrl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun provideWeatherApi(
        @WeatherApiUrl weatherApiUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(weatherApiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(WeatherApi::class.java)
    }


    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideGson(
        weatherResultDeserializer: WeatherResultDeserializer
    ) =
        GsonBuilder()
            .registerTypeAdapter(
                WeatherResult::class.java,
                weatherResultDeserializer
            ).create()

    @Singleton
    @Provides
    @WeatherApiUrl
    fun provideStaticApiUrl() = "http://api.openweathermap.org/data/2.5/"

}