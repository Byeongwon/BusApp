package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class Response (

    @SerializedName("header")
    val header: Header? = null,

    @SerializedName("body")
    val body: Body? = null

)