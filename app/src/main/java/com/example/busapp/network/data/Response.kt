package com.example.busapp.network.data

import com.google.gson.annotations.SerializedName

data class Response (

    @SerializedName("header")
    var header: Header? = null,

    @SerializedName("body")
    var body: Body? = null

)