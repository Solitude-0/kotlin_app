package com.example.myapplication

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.common.utils.LoadingUtils
import com.example.myapplication.common.utils.SpUtils
import com.example.myapplication.common.utils.SpUtils.getValue
import com.example.myapplication.databinding.LoginActivityBinding
import com.example.myapplication.exception.GlobalExceptionHandler
import com.example.myapplication.model.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<LoginActivityBinding>() {

    private lateinit var viewModel: LoginViewModel
    override fun onCreateViewBinding(): LoginActivityBinding {
        Thread.setDefaultUncaughtExceptionHandler(GlobalExceptionHandler(this))

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val username = getValue(this@LoginActivity.applicationContext,"username")
        val password = getValue(this@LoginActivity.applicationContext,"password")
        Log.d("response", "Response Body: $username++$password")
        lifecycleScope.launch {
            if (viewModel.login(
                    username,
                    password
                )
            ) {
                navigateToMainActivity()
            }
        }
        return LoginActivityBinding.inflate(layoutInflater)
    }

    override fun initView(binding: LoginActivityBinding) {
        finish()
        super.initView(binding)

        binding.password.setOnFocusChangeListener { view, hasFocus ->
            view.setBackgroundResource(if (hasFocus) R.drawable.input_border_active else R.drawable.input_border)
        }


        binding.loginButton.setOnClickListener {
            LoadingUtils.show(this)
            lifecycleScope.launch {
                if (viewModel.login(binding.username.text.toString(),binding.password.text.toString())) {
                    SpUtils.setValue(this@LoginActivity.applicationContext,"username",binding.username.text.toString())
                    SpUtils.setValue(this@LoginActivity.applicationContext,"password",binding.password.text.toString())
                    navigateToMainActivity()
                }
                LoadingUtils.cancel()
            }

        }
    }


    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
