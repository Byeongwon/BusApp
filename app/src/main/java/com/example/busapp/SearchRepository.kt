package com.example.busapp

import com.example.busapp.network.NetworkManager
import com.example.busapp.network.data.BusStop
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

    val getBusStops: Single<BusStop> =
        NetworkManager.getBusStopInfoService().getBusStopInfo(serviceKey, cityCode, "", numOfRows)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}