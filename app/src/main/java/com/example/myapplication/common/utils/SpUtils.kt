package com.example.myapplication.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.LoginActivity
import com.example.myapplication.common.utils.SpUtils.setValue


object SpUtils {


    private fun getSp(context: Context): SharedPreferences? {
        return context.getSharedPreferences("SpUtil", Context.MODE_PRIVATE);
    }


    fun setValue(context: Context, key: String,value: String){
        val editor = getSp(context)?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }
    fun getValue(context: Context, key: String): String {
      return  getSp(context)?.getString(key, "") ?: ""
    }
}


