package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.common.utils.LoadingUtils
import com.example.myapplication.common.utils.ToastUtils
import com.example.myapplication.databinding.LoginActivityBinding
import com.example.myapplication.model.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<LoginActivityBinding, LoginViewModel>(LoginViewModel::class) {

    override fun onCreateViewBinding(): LoginActivityBinding {

        return LoginActivityBinding.inflate(layoutInflater)
    }

    override fun initView(binding: LoginActivityBinding) {
        binding.password.setOnFocusChangeListener { view, hasFocus ->
            view.setBackgroundResource(if (hasFocus) R.drawable.input_border_active else R.drawable.input_border)
        }
        binding.loginButton.setOnClickListener {
            LoadingUtils.show(this)
            lifecycleScope.launch {
                val success = viewModel.login(binding.username.text.toString(), binding.password.text.toString())
                LoadingUtils.cancel()
                if (success) {
                    navigateToActivity(MainActivity::class.java)
                } else {
                    ToastUtils.error(application, "登录失败")
                }

            }

        }
    }


}
