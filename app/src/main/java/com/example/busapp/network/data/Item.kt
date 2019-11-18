package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class Item (

    @SerializedName("gpslati")
    var gpslati: Double = 0.0,

    @SerializedName("gpslong")
    var gpslong: Double = 0.0,

    @SerializedName("nodeid")
    var nodeid: String = "",

    @SerializedName("nodenm")
    var nodenm: String = "",

    @SerializedName("nodeno")
    var nodeno: Int = 0
)