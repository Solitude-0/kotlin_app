package com.example.myapplication.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.common.enums.ToastType

object ToastUtils {


    private var currentToast: Toast? = null
    fun success(context: Context, message: String? = "操作成功") =
        message?.let { getToast(context, it, ToastType.SUCCESS) }

    fun info(context: Context, message: String) = getToast(context, message, ToastType.INFO)
    fun error(context: Context, message: String? = "操作失败") =
        message?.let { getToast(context, it, ToastType.ERROR) }

    @SuppressLint("InflateParams")
    private fun getToast(context: Context, message: String, type: ToastType) {
        if (currentToast != null) {
            currentToast?.cancel()
        }
        currentToast = Toast(context)
        currentToast!!.duration = Toast.LENGTH_SHORT
        currentToast!!.setGravity(Gravity.TOP, 0, 0)
        val toastLayout = LayoutInflater.from(context).inflate(R.layout.common_toast, null)
        val textView = toastLayout.findViewById<TextView>(R.id.vt_message)
        textView.text = message
        // 根据ToastType设置背景
        textView.setBackground(type)
        currentToast!!.setView(toastLayout)
        currentToast!!.show()
    }

    private fun TextView.setBackground(type: ToastType) {
        when (type) {
            ToastType.SUCCESS -> {
                setBackgroundResource(R.drawable.toast_success)
                setTextColor(0xFF44BC6C.toInt())
            }

            ToastType.INFO -> {
                setBackgroundResource(R.drawable.toast_info)
                setTextColor(0xFF44BC6C.toInt())
            }

            ToastType.ERROR -> {
                setBackgroundResource(R.drawable.toast_error)
                setTextColor(0xFFE80D0D.toInt())
            }
        }

    }

}