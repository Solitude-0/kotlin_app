package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.SplashActivityBinding
import com.example.myapplication.common.utils.SpUtils
import java.lang.ref.WeakReference

class WelcomeActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private lateinit var binding: SplashActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.splashText.text= SpUtils.getValue("loginUserName")

        println(SpUtils.getValue("loginUserName"))
        SpUtils.getValue("loginUserName")?.let { Log.i("拿到的缓存", it) }
        handler = MyHandler(this)
        val thread = Thread(Runnable {
            Thread.sleep(2000)
            handler?.sendEmptyMessage(0)
        })
        thread.start()
    }

    internal class MyHandler(activity: WelcomeActivity) : Handler(Looper.getMainLooper()) {
        private val mActivity: WeakReference<WelcomeActivity> = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            if (msg.what == 0) {
                val activity = mActivity.get()
                activity?.navigateToMainActivity()
            }

        }
    }

    private fun navigateToMainActivity() {
        // Navigate to MainActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
