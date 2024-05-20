package com.example.myapplication.model


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alibaba.fastjson.JSON

import com.example.myapplication.api.ApiService

import com.example.myapplication.domain.LoginBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LoginViewModel : ViewModel() {

    private var loginBody: MutableLiveData<LoginBody> = MutableLiveData(LoginBody("111", "222"))
    fun getLoginBody(): MutableLiveData<LoginBody> {
        return loginBody;
    }

    suspend fun login(): Boolean  =  withContext(Dispatchers.IO){
        return@withContext try {
            val response = ApiService.login(JSON.toJSONString(loginBody.value))
            //执行其他业务逻辑
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}