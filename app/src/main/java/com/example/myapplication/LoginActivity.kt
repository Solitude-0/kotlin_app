package com.example.myapplication

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.common.utils.LoadingUtils
import com.example.myapplication.databinding.LoginActivityBinding
import com.example.myapplication.exception.GlobalExceptionHandler
import com.example.myapplication.model.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<LoginActivityBinding>() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateViewBinding(): LoginActivityBinding {
        Thread.setDefaultUncaughtExceptionHandler(GlobalExceptionHandler(this))
        return LoginActivityBinding.inflate(layoutInflater)
    }

    override fun initView(binding: LoginActivityBinding) {
        super.initView(binding)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.password.setOnFocusChangeListener { view, hasFocus ->
            view.setBackgroundResource(if (hasFocus) R.drawable.input_border_active else R.drawable.input_border)
        }

        binding.loginButton.setOnClickListener {
            LoadingUtils.show(this@LoginActivity)
            lifecycleScope.launch {

                if (viewModel.login()) {
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
