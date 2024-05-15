package com.example.myapplication.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class ResponseInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            // 处理错误响应，比如重试、抛出异常等
            throw IOException("Unexpected code " + response.code)
        }

        return response
    }
}