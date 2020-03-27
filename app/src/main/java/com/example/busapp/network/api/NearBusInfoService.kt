package com.example.busapp.network.api

import com.example.busapp.network.data.ResponseBusStop
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NearBusInfoService {

    @GET("BusSttnInfoInqireService/getCrdntPrxmtSttnList")
    fun getBusStopInfo(
        @Query("serviceKey") serviceKey: String,
        @Query("gpsLati") gpsLati: Double,
        @Query("gpsLong") gpsLong: Double
    ): Single<ResponseBusStop>
}