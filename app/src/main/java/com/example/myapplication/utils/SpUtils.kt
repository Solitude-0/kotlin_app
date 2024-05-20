package com.example.myapplication.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


object SpUtils {

    private var sp: SharedPreferences? = null

    private fun getSp(context: Context): SharedPreferences? {
        if (sp == null) {
            sp = context.getSharedPreferences("SpUtil", Context.MODE_PRIVATE);
        }
        return sp;
    }


    fun setValue(context: Context,key:String,value:String) {
        //存入数据
        val edit = getSp(context)?.edit()
        edit?.putString(key, value);
        edit?.apply();

    }
    fun getValue(key:String): String? {
       return sp?.getString(key,"")
    }
}


