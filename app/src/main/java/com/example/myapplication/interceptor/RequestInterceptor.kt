package com.example.myapplication.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // 添加请求头
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ")
            .build()
        return chain.proceed(request)
    }
}