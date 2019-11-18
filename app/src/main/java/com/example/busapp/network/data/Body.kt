package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class Body (

    @SerializedName("items")
    var items: Items? = null,

    @SerializedName("numOfRows")
    var numOfRows: Int = 0,

    @SerializedName("pageNo")
    var pageNo: Int = 0,

    @SerializedName("totalCount")
    var totalCount: Int = 0
)