package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapter.ListAdapter
import com.example.myapplication.common.BaseFragment
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.common.utils.SpUtils
import com.example.myapplication.databinding.FragmentFunBinding
import com.example.myapplication.model.ListViewModel
import com.example.myapplication.model.LoginViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, ListViewModel>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    override val viewModelClass: Class<ListViewModel>
        get() = ListViewModel::class.java

    override fun initEvent(binding: FragmentHomeBinding) {
        super.initEvent(binding)
    }

    override fun initObserve(viewModel: ListViewModel) {
        super.initObserve(viewModel)
    }

}