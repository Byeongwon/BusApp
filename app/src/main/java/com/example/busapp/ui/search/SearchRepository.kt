package com.example.busapp.ui.search

import com.example.busapp.network.NetworkManager
import com.example.busapp.network.data.ArriveInfo
import com.example.busapp.network.data.ResponseBusStop
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRepository {

    companion object {
        /**
         * API 서비스키
         */
        private const val serviceKey =
            "ciQLMBFjiJOBw4loD00NCS6fod8n3He99MysmrVuHvQxiSEou9CThpr0gmguv7TiXSI3vyjECcbmMMHAdu+3+w=="
        /**
         * 도시 코드(대구 = 22)
         */
        private const val cityCode = 22
        /**
         * 결과 요청 갯수
         */
        private const val numOfRows = 10000
    }

    val getBusStops: Single<ResponseBusStop> =
        NetworkManager.getBusStopInfoService().getBusStopInfo(
            serviceKey,
            cityCode, "",
            numOfRows
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun getArriveInfo(nodeId: String): Single<ArriveInfo> {
        return NetworkManager.getBusArriveInfoService().getArriveInfo(
            serviceKey,
            cityCode,
            nodeId
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun getBusStopNearByCurrentLocation(latitude: Double, longitude: Double): Single<ResponseBusStop> {
        return NetworkManager.getBusNearInfoService().getBusStopInfo(
            serviceKey,
            latitude,
            longitude
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}