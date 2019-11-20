package com.example.busapp.network

import com.example.busapp.BuildConfig
import com.example.busapp.network.api.BusInfoService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkManager {
    private const val BASE_URL = "http://openapi.tago.go.kr/openapi/service/"

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val client = createOkHttpClient()

    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    /**
     * OkHttpClient 생성
     * 통신 Response 데이터 로그를 볼 수 있는 Client 생성
     */
    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .addInterceptor(ResponseInterceptor())
            .build()
    }

    private val busStopInfoService = mRetrofit.create(BusInfoService::class.java)

    fun getBusStopInfoService(): BusInfoService {
        return busStopInfoService
    }
}