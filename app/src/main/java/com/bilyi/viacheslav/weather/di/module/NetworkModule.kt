package com.bilyi.viacheslav.weather.di.module

import android.content.Context
import com.bilyi.viacheslav.weather.data.CachingInterceptor
import com.bilyi.viacheslav.weather.data.weather.WeatherApi
import com.bilyi.viacheslav.weather.data.weather.gson.ForecastWeatherResult
import com.bilyi.viacheslav.weather.data.weather.gson.ForecastWeatherResultDeserializer
import com.bilyi.viacheslav.weather.data.weather.gson.WeatherResult
import com.bilyi.viacheslav.weather.data.weather.gson.WeatherResultDeserializer
import com.bilyi.viacheslav.weather.di.qualifier.WeatherApiKey
import com.bilyi.viacheslav.weather.di.qualifier.WeatherApiUrl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideWeatherApi(
        retrofit: Retrofit
    ): WeatherApi =
        retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @WeatherApiUrl weatherApiUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(weatherApiUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun providesOkHttpClient(
        cache: Cache,
        cachingInterceptor: CachingInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .cache(cache)
            .addInterceptor(cachingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideCache(
        context: Context
    ): Cache =
        Cache(context.cacheDir, CACHE_SIZE)

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideGson(
        weatherResultDeserializer: WeatherResultDeserializer,
        forecastWeatherResultDeserializer: ForecastWeatherResultDeserializer
    ): Gson =
        GsonBuilder()
            .registerTypeAdapter(
                WeatherResult::class.java,
                weatherResultDeserializer
            )
            .registerTypeAdapter(
                ForecastWeatherResult::class.java,
                forecastWeatherResultDeserializer
            )
            .create()

    @Singleton
    @Provides
    @WeatherApiKey
    fun provideWeatherApiKey() = "491b0b43ad4d443d1b73298c9e3323e1"

    @Singleton
    @Provides
    @WeatherApiUrl
    fun provideWeatherBaseApiUrl() = "http://api.openweathermap.org/data/2.5/"

    companion object {

        private const val CACHE_SIZE: Long = 10 * 1024 * 1024
    }
}