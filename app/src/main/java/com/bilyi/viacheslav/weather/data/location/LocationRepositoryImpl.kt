package com.bilyi.viacheslav.weather.data.location

import com.bilyi.viacheslav.weather.domain.LatLng
import com.bilyi.viacheslav.weather.domain.LocationRepository
import io.reactivex.Observable
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val fusedDataSource: FusedLocationDataSource,
    private val preferencesDataSource: SharedPreferencesLocationDataSource
) : LocationRepository {

    override fun getLatLng(): Observable<LatLng> {
        return Observable.merge(
            preferencesDataSource.getLastLatLngSingle()
                .toObservable(),

            fusedDataSource.getLatLng()
                .doOnNext {
                    preferencesDataSource.saveLatLng(it)
                }
        )
    }

}