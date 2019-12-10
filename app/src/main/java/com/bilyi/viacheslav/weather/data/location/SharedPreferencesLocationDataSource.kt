package com.bilyi.viacheslav.weather.data.location

import android.content.Context
import android.content.SharedPreferences
import com.bilyi.viacheslav.weather.domain.LatLng
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SharedPreferencesLocationDataSource @Inject constructor(
    private val context: Context
) {

    private val preferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_FILE_NAME,
        Context.MODE_PRIVATE
    )

    private val editor = preferences.edit()


    fun getLastLatLng() = LatLng(
        latitude = preferences.getFloat(LATITUDE_KEY, DEFAULT_LATITUDE).toDouble(),
        longitude = preferences.getFloat(LONGITUDE_KEY, DEFAULT_LONGITUDE).toDouble()
    )

    fun getLastLatLngSingle() = Single.fromCallable { getLastLatLng() }

    fun saveLatLng(latLng: LatLng) {
        editor.putFloat(LATITUDE_KEY, latLng.latitude.toFloat())
        editor.putFloat(LONGITUDE_KEY, latLng.longitude.toFloat())
    }

    fun saveLatLngCompletable(latLng: LatLng) = Completable.fromAction { saveLatLng(latLng) }

    companion object {
        const val PREFERENCES_FILE_NAME = "last_location"

        const val DEFAULT_LATITUDE = 50.0f
        const val DEFAULT_LONGITUDE = 36.5f

        const val LATITUDE_KEY = "latitude"
        const val LONGITUDE_KEY = "longitude"

    }

}