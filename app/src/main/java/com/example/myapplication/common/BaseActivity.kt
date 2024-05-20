package com.example.myapplication.common

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化ViewBinding
        binding = onCreateViewBinding()
        setContentView(binding.root)
        initView(binding)
    }
    protected abstract fun onCreateViewBinding(): VB
    protected open fun initView(binding: VB) {}

}