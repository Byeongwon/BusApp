package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class Items (

    @SerializedName("item")
    var item: List<Item>? = null
)