package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.SplashActivityBinding
import java.lang.ref.WeakReference

class WelcomeActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private lateinit var binding: SplashActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize handler with the main looper
        handler = MyHandler(this)

        // Create and start the thread
        val thread = Thread(Runnable {
            // Perform background tasks here
            // For example, sending messages to handler
            // For demonstration, sending a message after 3 seconds
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
