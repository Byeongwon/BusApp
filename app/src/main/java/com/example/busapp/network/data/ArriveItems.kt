package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class ArriveItems (
    @SerializedName("item")
    val item: List<ArriveItem>? = null
)