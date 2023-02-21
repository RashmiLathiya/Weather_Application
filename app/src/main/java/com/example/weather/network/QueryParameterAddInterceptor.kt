package com.example.weather.network

import okhttp3.Interceptor
import okhttp3.Response

class QueryParameterAddInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val APP_ID = "f5cb0b965ea1564c50c6f1b74534d823"
        val url = chain.request().url().newBuilder()
                .addQueryParameter("appid", APP_ID)
                .build()

        val request = chain.request().newBuilder()
                .url(url)
                .build()

        return chain.proceed(request)
    }
}