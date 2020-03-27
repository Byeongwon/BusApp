package com.example.busapp.ui.busmap

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.busapp.ui.busmap.model.NearBusStop
import com.example.busapp.ui.search.SearchRepository
import com.google.android.gms.maps.model.LatLng

/**
 * 버스 마커 처리용 ViewModel
 */
class BusMapViewModel : ViewModel() {
    // 여기서 구현 필요.
    private val searchRepository = SearchRepository()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    fun updateNearBusStop(latlng: LatLng, busMapper: BusMapHelper) {
        loadingVisibility.value = View.VISIBLE
        val disposable =
            searchRepository.getBusStopNearByCurrentLocation(latlng.latitude, latlng.longitude)
                .subscribe({ nearInfo ->
                    loadingVisibility.value = View.GONE
                    val data = nearInfo.response?.body?.items?.item
                    if (data.isNullOrEmpty()) {
                        return@subscribe
                    }
                    data.map { NearBusStop(it.nodenm, it.nodeid, it.gpslati, it.gpslong) }
                        .forEach { busMapper.addMarker(it) }
                }, { throwable ->
                    loadingVisibility.value = View.GONE
                    Log.d("ERROR", throwable.toString())
                })
    }
}