package com.example.busapp.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.maps.model.LatLng

class LocationProvider {

    companion object {
        private var instance: LocationProvider? = null
        private var locationManager : LocationManager? = null

        @JvmStatic
        fun init(ctx: Context) {
            locationManager = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }

        @JvmStatic
        fun getInstance() : LocationProvider {
            return instance
                ?: LocationProvider().also { instance = it }
        }

        @JvmStatic
        fun stop() {
            instance = null
        }
    }

    /**
     * 현재 좌표 정보를 반환한다.
     */
    @SuppressLint("MissingPermission")
    fun getLastLocation() : Location? {
        val lastLocation = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val networkdLocation = locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        return lastLocation ?: networkdLocation
    }

    /**
     * 현재 WGS84 좌표 정보를 반환한다.
     */
    fun getLastLocationLatlng() : LatLng {
        val location = getLastLocation()
        return LatLng(location?.latitude ?: 0.0, location?.longitude ?: 0.0)
    }
}