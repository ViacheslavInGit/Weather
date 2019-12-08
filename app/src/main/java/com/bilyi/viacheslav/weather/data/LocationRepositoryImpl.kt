package com.bilyi.viacheslav.weather.data

import android.location.Location
import android.util.Log
import com.bilyi.viacheslav.weather.domain.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.Single
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationRepository {

    override fun getLocation(): Single<Location> {
        return Single.create { emitter ->

            fusedLocationClient.lastLocation.addOnCompleteListener { task ->

                val result = task.result
                val exception = task.exception

                when {
                    result == null -> Log.d("###", "result is null")
                    task.isSuccessful -> emitter.onSuccess(result)

                    exception == null -> Log.d("###", "exception is null")
                    else -> emitter.onError(exception)
                }
            }
        }
    }
}