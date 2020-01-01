package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class ArriveItem(
    // 도착 예정 남은 정류장 갯수
    @SerializedName("arrprevstationcnt")
    val arrprevstationcnt: Int = 0,

    // 도착 예정 남은 시간
    @SerializedName("arrtime")
    val arrtime: Long = 0,

    // 정류소 아이디
    @SerializedName("nodeid")
    val nodeid: String = "",

    // 정류소 이름
    @SerializedName("nodenm")
    val nodenm: String = "",

    // 버스 아이디
    @SerializedName("routeid")
    val routeid: String = "",

    // 버스 번호
    @SerializedName("routeno")
    val routeno: String = "",

    // 버스 타입 (급행, 일반 등)
    @SerializedName("routetp")
    val routetp: String = "",

    // 버스 차량 타입 (저상버스 등)
    @SerializedName("vehicletp")
    val vehicletp: String = ""
)