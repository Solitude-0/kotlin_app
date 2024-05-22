package com.example.myapplication


import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.databinding.LoginActivityBinding
import com.example.myapplication.databinding.MainActivityBinding
import com.example.myapplication.model.LoginViewModel
import com.example.myapplication.ui.fragment.FunFragment
import com.example.myapplication.ui.fragment.HomeFragment
import com.example.myapplication.ui.fragment.MyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity<MainActivityBinding, LoginViewModel>(LoginViewModel::class) {

    override fun onCreateViewBinding(): MainActivityBinding {
        return MainActivityBinding.inflate(layoutInflater)
    }

    override fun initData(binding: MainActivityBinding) {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        val navView: BottomNavigationView = binding.navView
        loadFragment(HomeFragment())
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.navigation_dashboard -> {
                    loadFragment(FunFragment())
                    true
                }

                R.id.navigation_notifications -> {
                    loadFragment(MyFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}


