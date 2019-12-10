package com.bilyi.viacheslav.weather.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import javax.inject.Inject

//todo rename
class PermissionsManager @Inject constructor(
    private val context: Context
) {

    fun isLocationPermissionEnabled(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}