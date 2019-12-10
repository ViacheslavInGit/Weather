package com.bilyi.viacheslav.weather.data.location

import android.location.Location
import android.util.Log
import com.bilyi.viacheslav.weather.domain.LatLng
import com.bilyi.viacheslav.weather.util.PermissionsManager
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.Single
import io.reactivex.SingleEmitter
import javax.inject.Inject

class FusedLocationDataSource @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val permissionsManager: PermissionsManager
) {

    fun getLatLng(): Single<LatLng> {
        return Single.create { emitter ->

            if (permissionsManager.isLocationPermissionEnabled()) {
                listenLocationResult(emitter)
            } else {
                Log.d("###", "locationPermissions not enabled")
                emitter.onError(Exception("locationPermissions not enabled"))
            }
        }
    }

    private fun listenLocationResult(emitter: SingleEmitter<LatLng>) {
        fusedLocationClient.lastLocation.addOnCompleteListener { task ->

            Log.d("###", "loc result ${task.result}")

            val result = task.result
            val exception = task.exception

            when {
                result == null -> Log.e("###", "result is null")
                task.isSuccessful -> emitter.onSuccess(result.toLatLng())

                exception == null -> Log.e("###", "exception is null")
                else -> emitter.onError(exception)
            }
        }
    }
}

fun Location.toLatLng() = LatLng(
    latitude = this.latitude,
    longitude = this.longitude
)