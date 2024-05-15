package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.alibaba.fastjson.JSON
import com.example.myapplication.databinding.LoginActivityBinding
import com.example.myapplication.exception.GlobalExceptionHandler
import com.example.myapplication.utils.CommonUtils
import com.example.myapplication.utils.HttpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread.setDefaultUncaughtExceptionHandler(GlobalExceptionHandler(this))

        binding.password.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.input_border_active)
            } else {
                view.setBackgroundResource(R.drawable.input_border)
            }
        }
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val hashMap = HashMap<String, String>();
                hashMap["username"] = "xambadmin"
                hashMap["password"] = "123456"
                hashMap["code"] = "0"
                hashMap["loginType"] = "2"
                HttpUtils.post("http://192.168.1.34:8025/login", JSON.toJSONString(hashMap)) { res, e ->
                    if (e != null) {
                        Log.e("LoginActivity", "登录失败: ${e.message}")
                        // Show error message to the user
                        lifecycleScope.launch(Dispatchers.Main) {
                            CommonUtils.showToast(this@LoginActivity, "登录成功") // 使用this@MyActivity来确保是Activity的上下文
                        }

                    } else {
                        // Check if response is successful
                        if (!res.isNullOrEmpty()) {
                            Log.i("LoginActivity", "登录成功: $res")
                            // Show success message to the user
                            lifecycleScope.launch(Dispatchers.Main) {
                                CommonUtils.showToast(
                                    this@LoginActivity,
                                    "登录成功"
                                ) // 使用this@MyActivity来确保是Activity的上下文
                            }

                            // Navigate to next activity or perform further actions
                        } else {
                            Log.e("LoginActivity", "登录失败: 响应为空")
                            // Show error message to the user
                            lifecycleScope.launch(Dispatchers.Main) {
                                CommonUtils.showToast(
                                    this@LoginActivity,
                                    "登录成功"
                                ) // 使用this@MyActivity来确保是Activity的上下文
                            }

                        }
                    }
                }
            } else {
                // Show error message if username or password is empty
                lifecycleScope.launch(Dispatchers.Main) {
                    CommonUtils.showToast(this@LoginActivity, "登录成功") // 使用this@MyActivity来确保是Activity的上下文
                }

            }
        }


    }


}

