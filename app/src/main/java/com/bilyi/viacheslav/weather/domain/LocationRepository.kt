package com.bilyi.viacheslav.weather.domain

import android.location.Location
import io.reactivex.Single

interface LocationRepository {

    fun getLocation(): Single<Location>

}