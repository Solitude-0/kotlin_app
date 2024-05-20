package com.example.myapplication.exception

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull

class GlobalExceptionHandler(private val applicationContext: Context) : Thread.UncaughtExceptionHandler {

    private val uiHandler = Handler(Looper.getMainLooper())

    override fun uncaughtException(t: Thread, e: Throwable) {
        // 先记录异常信息（这里省略）

        // 在 UI 线程上显示 AlertDialog
        uiHandler.post {
            showErrorMessageDialog(e)

            // 如果需要，可以调用默认的 UncaughtExceptionHandler
            // defaultHandler?.uncaughtException(t, e)

            // 通常在这里不会重启应用，因为系统会在所有未捕获的异常处理完毕后终止应用
            // 但如果您有特定的逻辑需要执行，可以在这里添加
        }
    }

    private fun showErrorMessageDialog(@NonNull e: Throwable) {
        AlertDialog.Builder(applicationContext)
            .setTitle("发生错误")
            .setMessage("应用遇到了一个问题，详情如下：\n\n" + e.toString())
            .setPositiveButton("确定", null)
            .show()
    }
}