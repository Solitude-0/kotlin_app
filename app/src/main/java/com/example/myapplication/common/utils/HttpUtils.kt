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

    fun <T> get(url: String, params: Map<String, String>? = null, clazz: Class<T>): ApiResponse<T>? {
        val fullUrl = buildFullUrl(url, params)
        val request = Request.Builder()
            .url(fullUrl)
            .get()
            .build()
        return executeRequest(request, clazz)
    }

    fun <T> post(url: String, params: String?, clazz: Class<T>): ApiResponse<T>? {
        val requestBody = params?.toRequestBody("application/json".toMediaTypeOrNull())
        val request = requestBody?.let {
            Request.Builder()
                .url(url)
                .post(it)
                .build()
        }
        return request?.let { executeRequest(it, clazz) }
    }


    private fun <T> executeRequest(request: Request, clazz: Class<T>): ApiResponse<T> {
        Log.d("HttpUtils", "Request URL: ${request.url}")
        Log.d("HttpUtils", "Request Method: ${request.method}")
        request.headers.names().forEach { name ->
            Log.d("HttpUtils", "Request Header: $name: ${request.headers[name]}")
        }
        request.body?.let { requestBody ->
            Log.d("HttpUtils", "Request Body: ${requestBodyToString(requestBody)}")
        }
        try {
            val response: Response = client.newCall(request).execute()
            return if (response.isSuccessful) {
                val responseBody = response.body?.string()
                Log.d("HttpUtils", "Response Body: $responseBody")
                responseBody?.let { ApiResponse.parse(it, clazz) } ?: ApiResponse.error(
                    response.code,
                    response.message
                )
            } else {
                Log.e("HttpUtils", "Request failed: ${response.code} - ${response.message}")
                ApiResponse.error(response.code, response.message)
            }
        } catch (e: Exception) {
            Log.e("HttpUtils", "Request failed: ${e.message}")
            return ApiResponse.error(400, "请求失败")
        }
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
