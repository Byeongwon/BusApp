package com.example.busapp.network

import com.example.busapp.network.api.BusInfoService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkManager {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val client = OkHttpClient.Builder()
        .addInterceptor(ResponseInterceptor())
        .build()

    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://openapi.tago.go.kr/openapi/service/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    private val busStopInfoService = mRetrofit.create(BusInfoService::class.java)


    fun getRetrofit(): Retrofit {
        return mRetrofit
    }

    fun getBusStopInfoService(): BusInfoService {
        return busStopInfoService
    }
}