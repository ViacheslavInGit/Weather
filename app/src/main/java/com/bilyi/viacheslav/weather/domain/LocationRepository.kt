package com.bilyi.viacheslav.weather.domain

import io.reactivex.Single

interface LocationRepository {

    fun getLatLng(): Single<LatLng>

}