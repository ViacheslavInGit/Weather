package com.bilyi.viacheslav.weather.data

import android.location.Location
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
                if (task.isSuccessful) {
                    emitter.onSuccess(task.result!!)
                } else {
                    emitter.onError(task.exception!!)
                }
            }
        }
    }
}