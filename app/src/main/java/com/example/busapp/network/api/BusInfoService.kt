package com.example.busapp.network.api

import com.example.busapp.network.data.BusStop
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusInfoService {

    @GET("BusSttnInfoInqireService/getSttnNoList")
    fun getBusStopInfo(@Query("serviceKey") serviceKey: String,
                       @Query("cityCode") cityCode: Int,
                       @Query("nodeName", encoded = true) nodeName: String,
                       @Query("numOfRows") numOfRows: Int): Call<BusStop>
}