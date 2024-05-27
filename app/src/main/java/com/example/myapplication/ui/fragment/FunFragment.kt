package com.example.myapplication.ui.fragment

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.adapter.ContentListAdapter
import com.example.myapplication.common.BaseFragment
import com.example.myapplication.databinding.FragmentFunBinding
import com.example.myapplication.model.ListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FunFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FunFragment : BaseFragment<FragmentFunBinding, ListViewModel>() {

    private lateinit var adapter: ContentListAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFunBinding
        get() = FragmentFunBinding::inflate
    override val viewModelClass: Class<ListViewModel>
        get() = ListViewModel::class.java


    override fun initEvent(binding: FragmentFunBinding) {
        adapter = ContentListAdapter()
        binding.recyclerView.adapter = adapter
    }

    override fun initObserve(viewModel: ListViewModel) {
        viewModel.demo2.observe(viewLifecycleOwner) { dataList ->
            adapter.submitList(dataList)
        }
    }

    override fun initData(viewModel: ListViewModel) {
        viewModel.getList()
    }
}