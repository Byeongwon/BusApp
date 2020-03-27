package com.example.busapp.ui.busmap

import android.content.Context
import com.example.busapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

/**
 * 구글 맵 관련 지도 핸들링 클래스
 */
class BusMapHelper(context: Context) {

    // 기본 줌레벨 16
    private val ZOOM_LEVEL = 16f
    private var gMap: GoogleMap? = null
    private val ctx: Context = context

    fun setGoogleMap(googleMap: GoogleMap?) {
        gMap = googleMap ?: return
    }

    /**
     * 지도 이동
     * 특정 좌표를 중심점으로 지도를 이동한다.
     */
    fun moveCamera(latLng : LatLng) {
        gMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL))
    }

    /**
     * 마커 추가
     */
    fun addMarker(latLng : LatLng) {
        val markerOptions = MarkerOptions().also {
            it.position(latLng)
            // todo busMaker 추가 필요location-pin
            it.icon(BitmapDescriptorFactory.fromResource(R.drawable.location_pin))
        }
        currentMarker = gMap?.addMarker(markerOptions)
    }

    var currentMarker: Marker? = null
    var customMarker: Marker? = null

    /**
     * 마커 추가
     */
    fun addCustomMarker(latLng : LatLng) {
        moveCamera(latLng)
        customMarker?.remove()
        val markerOptions = MarkerOptions().also {
            it.position(latLng)
            // todo busMaker 추가 필요
            it.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
        }
        customMarker = gMap?.addMarker(markerOptions)
//        changeFitBounds()
    }

    /**
     * 마커 기준으로 지도 영역 조정
     */
    private fun changeFitBounds() {
        val bounds = LatLngBounds.Builder().also {
            it.include(currentMarker?.position)
            it.include(customMarker?.position)
        }.build()
        val width = ctx.resources.displayMetrics.widthPixels
        val height = ctx.resources.displayMetrics.heightPixels
        val padding = (width * 0.10).toInt()
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)
        gMap?.animateCamera(cameraUpdate)
    }

    /**
     * 중심점 좌표 처리
     * 좌표를 중심으로 지도를 이동하고 마커를 찍는다.
     */
    fun setCenterPoint(latLng : LatLng) {
        moveCamera(latLng)
        addCustomMarker(latLng)
    }
}