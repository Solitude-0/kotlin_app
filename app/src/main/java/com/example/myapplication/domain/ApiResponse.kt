package com.example.myapplication.domain

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class ApiResponse<T>(
    val code: Int,
    val data: T?,
    val message: String?
) {

    fun isSuccessful(): Boolean {
        return code in 200..299
    }

    companion object {
        private val gson = Gson()
        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(200, data, null)
        }

        fun <T> error(code: Int, message: String? = null): ApiResponse<T> {
            return ApiResponse(code, null, message)
        }

        fun <T> parse(jsonString: String, clazz: Class<T>): ApiResponse<T>? {
            val type = TypeToken.getParameterized(ApiResponse::class.java, clazz).type
            return gson.fromJson(jsonString, type)
        }
    }


}
