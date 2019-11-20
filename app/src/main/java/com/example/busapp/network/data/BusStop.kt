package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class BusStop(
    @SerializedName("response")
    val response: Response? = null
)