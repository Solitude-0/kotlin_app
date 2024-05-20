package com.example.myapplication.utils

import android.util.Log
import com.example.myapplication.interceptor.RequestInterceptor
import com.example.myapplication.interceptor.ResponseInterceptor
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import java.io.IOException

object HttpUtils {

    private val client = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .addInterceptor(ResponseInterceptor())
        .build()

    fun get(url: String, params: Map<String, String>? = null, callback: ((String?, Throwable?) -> Unit)?) {
        val fullUrl = buildFullUrl(url, params)
        val request = Request.Builder()
            .url(fullUrl)
            .get()
            .build()
        executeRequest(request, callback)
    }

    fun post(url: String, params: String, callback: ((String?, Throwable?) -> Unit)?) {
        val requestBody = params.toRequestBody("application/json".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        executeRequest(request, callback)
    }

    private fun buildFullUrl(url: String, params: Map<String, String>?): String {
        val httpUrlBuilder = url.toHttpUrlOrNull()?.newBuilder()
        params?.forEach { (key, value) ->
            httpUrlBuilder?.addQueryParameter(key, value)
        }
        return httpUrlBuilder?.build()?.toString() ?: url
    }

    private fun executeRequest(request: Request, callback: ((String?, Throwable?) -> Unit)?) {
        Log.d("HttpUtils", "Request URL: ${request.url}")
        Log.d("HttpUtils", "Request Method: ${request.method}")
        val headers = request.headers
        for (name in headers.names()) {
            Log.d("HttpUtils", "Request Header: $name: ${headers[name]}")
        }
        request.body?.let { requestBody ->
            Log.d("HttpUtils", "Request Body: ${requestBodyToString(requestBody)}")
        }
        // 异步执行请求
        client.newCall(request).enqueue(object : Callback {
            // http请求失败
            override fun onFailure(call: Call, e: IOException) {
                Log.d("HttpUtils", "Request failed: $e")
                callback?.invoke(null, e)
            }

            // http响应成功
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                Log.d("HttpUtils", "Response: $body")
                callback?.invoke(body, null)
            }
        })
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
