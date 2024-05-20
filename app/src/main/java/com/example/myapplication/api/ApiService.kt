package com.example.myapplication.api

import com.example.myapplication.common.utils.HttpUtils
import com.example.myapplication.domain.ApiResponse

object ApiService {
    fun login(params: String) = HttpUtils.post("http://breed.szsyhml.cn:8025/login", params, String::class.java)
}