package com.example.myapplication.common

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.myapplication.exception.GlobalExceptionHandler
import com.example.myapplication.model.LoginViewModel

import kotlin.reflect.KClass


abstract class BaseActivity<VB : ViewBinding, VM : ViewModel>(
    private val vmClass: KClass<VM>
) : AppCompatActivity() {

    private lateinit var binding: VB
    lateinit var viewModel: VM  // 声明为protected允许子类访问

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler(GlobalExceptionHandler(application))
        binding = onCreateViewBinding()
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[vmClass.java]
        setContentView(binding.root)
        initData(binding)
        initView(binding)
    }

    protected abstract fun onCreateViewBinding(): VB
    protected open fun initData(binding: VB) {}
    protected open fun initView(binding: VB) {}

    protected fun navigateToActivity(targetClass: Class<*>) {
        val intent = Intent(this, targetClass)
        startActivity(intent)
        finish()
    }


}