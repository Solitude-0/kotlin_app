package com.example.myapplication.common

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.myapplication.enums.ToastType
import com.example.myapplication.utils.ToastUtils
import com.xuexiang.xui.XUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {


    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化ViewBinding
        binding = onCreateViewBinding()
        setContentView(binding.root)
        initView(binding)
    }

    protected abstract fun onCreateViewBinding(): VB
    protected open fun initView(binding: VB) {}


    override fun onDestroy() {
        super.onDestroy()

    }

}