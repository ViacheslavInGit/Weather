package com.bilyi.viacheslav.weather.data.gson

data class WeatherResult(
    private val tempMix: Double,
    private val tempMax: Double,
    val description: String
) {
    val celsiusMin: Int
        get() = (tempMix + ABSOLUTE_ZERO).toInt()

    val celsiusMax: Int
        get() = (tempMax + ABSOLUTE_ZERO).toInt()

    companion object {
        const val ABSOLUTE_ZERO = -273.15
    }
}
