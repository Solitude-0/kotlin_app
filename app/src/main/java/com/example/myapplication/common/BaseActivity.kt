package com.example.myapplication.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.myapplication.exception.GlobalExceptionHandler

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {


    // 声明lateinit的ViewBinding属性
    private lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化ViewBinding
        binding = onCreateViewBinding()
        setContentView(binding.root)
    }
    protected abstract fun onCreateViewBinding(): VB
}