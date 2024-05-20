package com.example.myapplication.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.example.myapplication.R
import com.xuexiang.xui.widget.dialog.LoadingDialog

object LoadingUtils {

    @SuppressLint("StaticFieldLeak")
    private var loading: LoadingDialog? = null

    fun show(context: Context) {
        if (loading == null || !loading?.isLoading!!) {
            loading = getLoading(context)
        }
        loading?.show()
    }
    fun cancel() {
        loading?.cancel()
        loading = null // 考虑到可能在其他地方重新调用show，这里可以重置为null
    }

    private fun getLoading(context: Context): LoadingDialog {
        val dialog = LoadingDialog(context)
        val loadingView = LayoutInflater.from(context).inflate(R.layout.common_loding, null)
        dialog.setContentView(loadingView) // 假设有这样的方法
        return dialog
    }
}