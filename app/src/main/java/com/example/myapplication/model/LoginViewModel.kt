package com.example.myapplication.model


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON

import com.example.myapplication.api.ApiService
import com.example.myapplication.common.utils.SpUtils

import com.example.myapplication.domain.LoginBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var loginBody: MutableLiveData<LoginBody> = MutableLiveData()
    fun getLoginBody(): MutableLiveData<LoginBody> {
        return loginBody;
    }

    suspend fun login(username: String, password: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = ApiService.login(JSON.toJSONString(LoginBody(username, password)))
            Log.d("response", "Response Body: $response")
            SpUtils.setValue(getApplication<Application>().applicationContext, "username", username)
            SpUtils.setValue(getApplication<Application>().applicationContext, "password", password)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}