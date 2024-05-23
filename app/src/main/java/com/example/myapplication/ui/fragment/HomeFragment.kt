package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapter.ListAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.common.utils.SpUtils
import com.example.myapplication.model.ListViewModel
import com.example.myapplication.model.LoginViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: ListAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = ListAdapter()
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        viewModel.demo.observe(viewLifecycleOwner) { dataList ->
            // 当数据变化时，更新适配器
            adapter.submitList(dataList)
        }
        binding.loginButton.setOnClickListener {
            viewModel.getDataList(2)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getDataList(null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}