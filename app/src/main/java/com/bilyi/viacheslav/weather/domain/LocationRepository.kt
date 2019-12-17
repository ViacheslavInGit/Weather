package com.bilyi.viacheslav.weather.domain

import io.reactivex.Observable
import io.reactivex.Single

interface LocationRepository {

    fun getLatLng(): Observable<LatLng>

}