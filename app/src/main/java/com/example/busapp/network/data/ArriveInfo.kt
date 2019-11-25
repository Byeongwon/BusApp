package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class ArriveInfo (
    @SerializedName("response")
    val response: ArriveResponse? = null
)