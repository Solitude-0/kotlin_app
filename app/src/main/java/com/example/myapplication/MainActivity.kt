package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.exception.GlobalExceptionHandler

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 在这里可以使用 binding 来访问布局文件中的视图
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}


