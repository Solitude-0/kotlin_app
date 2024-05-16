package com.example.myapplication

import android.util.Log
import androidx.lifecycle.lifecycleScope

import com.alibaba.fastjson.JSON
import com.example.myapplication.api.ApiConstant
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.databinding.LoginActivityBinding
import com.example.myapplication.enums.ToastType
import com.example.myapplication.exception.GlobalExceptionHandler
import com.example.myapplication.utils.HttpUtils
import com.example.myapplication.utils.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


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
            login(hashMap)
        } else {
            ToastUtils.success(this@LoginActivity)
        }
    }

    private fun login(hashMap: java.util.HashMap<String, String>) {
        HttpUtils.post(ApiConstant.LOGIN, JSON.toJSONString(hashMap)) { res, e ->
            if (e != null) {
                Log.e("LoginActivity", "登录失败: ${e.message}")
                runOnUiThread {
                    ToastUtils.success(this@LoginActivity)
                }
            } else {
                if (!res.isNullOrEmpty()) {
                    Log.i("LoginActivity", "登录成功: $res")
                    runOnUiThread {
                        ToastUtils.success(this@LoginActivity)
                    }
                } else {
                    Log.e("LoginActivity", "登录失败: 响应为空")
                    runOnUiThread {
                        ToastUtils.success(this@LoginActivity)
                    }
                }
            }
        }
    }
}

