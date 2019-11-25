package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class ArriveItem(
    @SerializedName("arrprevstationcnt")
    val arrprevstationcnt: Int = 0,

    @SerializedName("arrtime")
    val arrtime: Long = 0,

    @SerializedName("nodeid")
    val nodeid: String = "",

    @SerializedName("nodenm")
    val nodenm: String = "",

    @SerializedName("routeid")
    val routeid: String = "",

    @SerializedName("routeno")
    val routeno: String = "",

    @SerializedName("routetp")
    val routetp: String = "",

    @SerializedName("vehicletp")
    val vehicletp: String = ""
)