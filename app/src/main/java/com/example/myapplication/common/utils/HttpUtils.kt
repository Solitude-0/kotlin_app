package com.example.myapplication.common.utils

import android.util.Log
import com.example.myapplication.common.interceptor.RequestInterceptor
import com.example.myapplication.common.interceptor.ResponseInterceptor
import com.example.myapplication.domain.ApiResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object HttpUtils {

    private val client = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .addInterceptor(ResponseInterceptor())
        .build()

    fun <T> get(url: String, params: Map<String, String>? = null, clazz: Class<T>) {
        val fullUrl = buildFullUrl(url, params)
        val request = Request.Builder()
            .url(fullUrl)
            .get()
            .build()
        executeRequest(request, clazz)
    }

    fun <T> post(url: String, params: String?, clazz: Class<T>) {
        val requestBody = params?.toRequestBody("application/json".toMediaTypeOrNull())
        val request = requestBody?.let {
            Request.Builder()
                .url(url)
                .post(it)
                .build()
        }
        if (request != null) {
            executeRequest(request, clazz)
        }
    }


    private fun <T> executeRequest(request: Request, clazz: Class<T>): ApiResponse<T>? {
        Log.d("HttpUtils", "Request URL: ${request.url}")
        Log.d("HttpUtils", "Request Method: ${request.method}")
        val headers = request.headers
        for (name in headers.names()) {
            Log.d("HttpUtils", "Request Header: $name: ${headers[name]}")
        }
        request.body?.let { requestBody ->
            Log.d("HttpUtils", "Request Body: ${requestBodyToString(requestBody)}")
        }
        val response: Response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val responseBody = response.body?.string()
            if (responseBody != null) {
                return ApiResponse.parse(responseBody, clazz)
            } else {
                // 如果响应失败，返回错误信息
                return ApiResponse.error(response.code, response.message)
            }
        }
        return ApiResponse.error(response.code, "请求失败")
    }


    private fun buildFullUrl(url: String, params: Map<String, String>?): String {
        val httpUrlBuilder = url.toHttpUrlOrNull()?.newBuilder()
        params?.forEach { (key, value) ->
            httpUrlBuilder?.addQueryParameter(key, value)
        }
        return httpUrlBuilder?.build()?.toString() ?: url
    }

    private fun requestBodyToString(requestBody: RequestBody): String {
        val buffer = Buffer()
        return try {
            requestBody.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "Unable to read request body"
        } finally {
            buffer.close()
        }
    }

}
