package com.bilyi.viacheslav.weather.data.location

import android.util.Log
import com.bilyi.viacheslav.weather.domain.LatLng
import com.bilyi.viacheslav.weather.domain.LocationRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val fusedDataSource: FusedLocationDataSource,
    private val preferencesDataSource: SharedPreferencesLocationDataSource
) : LocationRepository {

    override fun getLatLng(): Single<LatLng> {
        return updateLocation().onErrorComplete()
            .toSingle { preferencesDataSource.getLastLatLng() }
            .doOnSuccess { Log.d("###latLng", "$it") }
    }

    private fun updateLocation(): Completable {
        return fusedDataSource.getLatLng()
            .flatMapCompletable {
                preferencesDataSource.saveLatLngCompletable(it)
            }
    }

}