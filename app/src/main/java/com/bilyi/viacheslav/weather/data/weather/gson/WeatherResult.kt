package com.bilyi.viacheslav.weather.data.weather.gson

data class WeatherResult(
    private val temp: Double,
    private val tempMin: Double,
    private val tempMax: Double,
    val description: String
) {
    val currentCelsius: Int
        get() = (temp + ABSOLUTE_ZERO).toInt()

    val minCelsius: Int
        get() = (tempMin + ABSOLUTE_ZERO).toInt()

    val maxCelsius: Int
        get() = (tempMax + ABSOLUTE_ZERO).toInt()

    companion object {
        const val ABSOLUTE_ZERO = -273.15
    }
}
