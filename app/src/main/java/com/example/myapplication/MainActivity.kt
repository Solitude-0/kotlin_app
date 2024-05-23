package com.example.myapplication


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.common.BaseActivity
import com.example.myapplication.databinding.MainActivityBinding
import com.example.myapplication.model.LoginViewModel
import com.example.myapplication.ui.fragment.FunFragment
import com.example.myapplication.ui.fragment.HomeFragment
import com.example.myapplication.ui.fragment.MyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity<MainActivityBinding, LoginViewModel>(LoginViewModel::class) {
    private lateinit var viewPager: ViewPager2;
    private lateinit var bottomNavigationView: BottomNavigationView;
    override fun onCreateViewBinding(): MainActivityBinding {
        return MainActivityBinding.inflate(layoutInflater)
    }

    override fun initView(binding: MainActivityBinding) {

//        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = binding.navView
    }

    override fun initData(binding: MainActivityBinding) {
        // 创建一个Fragment列表
//        val fragments: MutableList<Fragment> = ArrayList()
//        fragments.add(HomeFragment())
//        fragments.add(FunFragment())
//        fragments.add(MyFragment())
//
//        viewPager.setAdapter(object : FragmentStateAdapter(this) {
//            override fun getItemCount(): Int {
//                return fragments.size
//            }
//
//            override fun createFragment(position: Int): Fragment {
//                return fragments[position]
//            }
//        })

//        viewPager.setCurrentItem(0, true)
//        viewPager.offscreenPageLimit = 1
        loadFragment(HomeFragment(), "首页")
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
//                    viewPager.setCurrentItem(0, true)
                    loadFragment(HomeFragment(), item.title)
                    true
                }

                R.id.navigation_dashboard -> {
//                    viewPager.setCurrentItem(1, true)
                    loadFragment(FunFragment(), item.title)
                    true
                }

                R.id.navigation_notifications -> {
//                    viewPager.setCurrentItem(2, true)
                    loadFragment(MyFragment(), item.title)
                    true
                }

                else -> {
                    false
                }
            }
        }

    }

    private fun loadFragment(fragment: Fragment, title: CharSequence?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        binding.titleBar.setTitle(title)
        binding.titleBar.setLeftVisible(false)
        fragmentTransaction.commit()
    }

}


