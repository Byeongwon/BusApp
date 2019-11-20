package com.example.busapp.network

import okhttp3.Interceptor
import okhttp3.Response


class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder()
        builder.addHeader("Content-Type", "application/json; charset=utf-8")
        builder.addHeader("Accept", "application/json; charset=utf-8")
        builder.method(original.method(), original.body())

        val request = builder.build()

        return chain.proceed(request)
    }
}