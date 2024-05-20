package com.example.myapplication

import android.content.Intent
import android.util.Log
import androidx.databinding.adapters.ProgressBarBindingAdapter
import com.alibaba.fastjson.JSON
import com.example.myapplication.api.ApiConstant
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.databinding.LoginActivityBinding
import com.example.myapplication.exception.GlobalExceptionHandler
import com.example.myapplication.utils.HttpUtils
import com.example.myapplication.utils.LoadingUtils
import com.example.myapplication.utils.SpUtils
import com.example.myapplication.utils.ToastUtils
import com.xuexiang.xui.XUI
import com.xuexiang.xui.widget.dialog.LoadingDialog


class LoginActivity : BaseActivity<LoginActivityBinding>() {
    override fun onCreateViewBinding(): LoginActivityBinding {

        Thread.setDefaultUncaughtExceptionHandler(GlobalExceptionHandler(this))
        return LoginActivityBinding.inflate(layoutInflater)
    }

    override fun initView(binding: LoginActivityBinding) {
        super.initView(binding)
        binding.password.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.input_border_active)
            } else {
                view.setBackgroundResource(R.drawable.input_border)
            }
        }
        binding.loginButton.setOnClickListener {
            // 模拟登录
            clickLogin()
        }
    }

    private fun clickLogin() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            val hashMap = HashMap<String, String>()
            hashMap["username"] = "xambadmin"
            hashMap["password"] = "123456"
            hashMap["code"] = "0"
            hashMap["loginType"] = "2"
            LoadingUtils.show(this)
            login(hashMap)
        }
    }

    private fun login(hashMap: java.util.HashMap<String, String>) {
        HttpUtils.post(ApiConstant.LOGIN, JSON.toJSONString(hashMap)) { res, e ->
            SpUtils.setValue(this@LoginActivity.applicationContext,"loginUserName","aaaa")
            LoadingUtils.cancel()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
//            if (e != null) {
//                Log.e("LoginActivity", "登录失败: ${e.message}")
//                runOnUiThread {
//                    ToastUtils.success(this)
//                }
//            } else {
//                if (!res.isNullOrEmpty()) {
//                    Log.i("LoginActivity", "登录成功: $res")
//                } else {
//                    Log.e("LoginActivity", "登录失败: 响应为空")
//                    runOnUiThread {
//                        ToastUtils.success(this@LoginActivity)
//                    }
//                }
//            }
        }
    }
}

