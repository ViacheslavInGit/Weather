package com.bilyi.viacheslav.weather.di.module

import com.bilyi.viacheslav.weather.MainActivity
import com.bilyi.viacheslav.weather.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    @ActivityScope
    fun contributeMainActivity(): MainActivity
}