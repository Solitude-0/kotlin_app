package com.example.myapplication.common

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.myapplication.exception.GlobalExceptionHandler
import kotlin.reflect.KClass


abstract class BaseActivity<VB : ViewBinding, VM : ViewModel>(
    private val vmClass: KClass<VM>
) : AppCompatActivity() {

    lateinit var binding: VB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler(GlobalExceptionHandler(application))
        binding = onCreateViewBinding()
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[vmClass.java]
        setContentView(binding.root)
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, com.xuexiang.xui.R.color.xui_config_color_titlebar)

        initView(binding)
        initData(binding)
    }

    protected abstract fun onCreateViewBinding(): VB
    protected open fun initData(binding: VB) {}
    protected open fun initView(binding: VB) {}

    protected fun navigateToActivity(targetClass: Class<*>) {
        val intent = Intent(this, targetClass)
        startActivity(intent)
        finish()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val focusedView = currentFocus
        if (focusedView != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
        }
        return super.onTouchEvent(event)
    }
}