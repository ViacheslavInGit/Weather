package com.bilyi.viacheslav.weather.util

import com.bilyi.viacheslav.weather.R

fun Int.getSuitableColor() =
    when {
        this > 30 -> R.color.hotWeather
        this > 20 -> R.color.heatWeather
        this > 8 -> R.color.averageWeather
        this > -5 -> R.color.coolWeather
        else -> R.color.frostWeather
    }