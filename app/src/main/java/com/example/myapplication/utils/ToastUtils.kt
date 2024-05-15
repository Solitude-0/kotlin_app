package com.example.myapplication.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R

object ToastUtils {

    enum class ToastType {
        SUCCESS, INFO, ERROR
    }

    fun success(context: Context, message: String = "操作成功") = getToast(context, message, ToastType.SUCCESS)
    fun info(context: Context, message: String) = getToast(context, message, ToastType.INFO)
    fun error(context: Context, message: String = "操作失败") = getToast(context, message, ToastType.ERROR)

    @SuppressLint("InflateParams")
    private fun getToast(context: Context, message: String, type: ToastType) {
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_LONG
        toast.setGravity(Gravity.TOP, 0, 0)

        val toastLayout = LayoutInflater.from(context).inflate(R.layout.view_toast, null)
        val textView = toastLayout.findViewById<TextView>(R.id.vt_message)
        textView.text = message

        // 根据ToastType设置背景
        textView.setBackground(type)

        toast.setView(toastLayout)
        toast.show()

    }

    private fun TextView.setBackground(type: ToastType) {
        when (type) {
            ToastType.SUCCESS -> setBackgroundResource(R.drawable.toast_success)
            ToastType.INFO -> setBackgroundResource(R.drawable.toast_info)
            ToastType.ERROR -> setBackgroundResource(R.drawable.toast_error)
        }

    }

}