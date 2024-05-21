package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.common.utils.SpUtils
import com.example.myapplication.databinding.SplashActivityBinding
import com.example.myapplication.model.LoginViewModel
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<SplashActivityBinding, LoginViewModel>(LoginViewModel::class) {

    private var handler: Handler? = null;
    override fun onCreateViewBinding(): SplashActivityBinding {
        return SplashActivityBinding.inflate(layoutInflater)
    }

    override fun initView(binding: SplashActivityBinding) {
        binding.splashText.text = SpUtils.getValue(application, "username")
    }

    override fun initData(binding: SplashActivityBinding) {
        val username = SpUtils.getValue(application, "username")
        val password = SpUtils.getValue(application, "password")
        if (username.isNotEmpty() && password.isNotEmpty()) {
            lifecycleScope.launch {
                val success = viewModel.login(username, password)
                if (success) {
                    navigateToActivity(MainActivity::class.java)
                }
            }
        } else {
            handler = ToLoginActivityHandle(this)
            handler?.sendEmptyMessageDelayed(0, 2000)
        }
    }

    @SuppressLint("HandlerLeak")
    inner class ToLoginActivityHandle(activity: SplashActivity) : Handler(Looper.getMainLooper()) {
        private var activity: WeakReference<SplashActivity> = WeakReference(activity)
        override fun handleMessage(msg: android.os.Message) {
            // 跳转到登录界面
            if (msg.what == 0) {
                activity.get()?.navigateToActivity(LoginActivity::class.java)
            }
        }
    }
}
