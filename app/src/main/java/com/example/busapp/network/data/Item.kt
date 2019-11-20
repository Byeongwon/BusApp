package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class Item (

    @SerializedName("gpslati")
    val gpslati: Double = 0.0,

    @SerializedName("gpslong")
    val gpslong: Double = 0.0,

    @SerializedName("nodeid")
    val nodeid: String = "",

    @SerializedName("nodenm")
    val nodenm: String = ""

)