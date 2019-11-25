package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class ArriveBody (
    @SerializedName("items")
    val items: ArriveItems? = null,

    @SerializedName("numOfRows")
    val numOfRows: Int = 0,

    @SerializedName("pageNo")
    val pageNo: Int = 0,

    @SerializedName("totalCount")
    val totalCount: Int = 0
)