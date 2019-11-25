package com.example.busapp.network.api

import com.example.busapp.network.data.ArriveInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ArriveInfoService {

    @GET("ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList")
    fun getArriveInfo(@Query("serviceKey") serviceKey: String,
                      @Query("cityCode") cityCode: Int,
                      @Query("nodeId") nodeId: String): Single<ArriveInfo>
}