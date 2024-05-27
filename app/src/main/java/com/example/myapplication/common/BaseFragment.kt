package com.example.myapplication.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    // Backing property for view binding
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    // lateinit property for view model
    protected lateinit var viewModel: VM

    // Abstract properties for binding inflater and view model class
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    abstract val viewModelClass: Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the view using the provided binding inflater
        _binding = bindingInflater.invoke(inflater, container, false)

        // Initialize the view model
        viewModel = ViewModelProvider(this)[viewModelClass]

        // Call abstract methods for event and observer initialization
        initEvent(binding)
        initObserve(viewModel)

        // Return the root view of the binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData(viewModel)
    }

    // Open methods to be optionally overridden by subclasses
    protected open fun initObserve(viewModel: VM) {}
    protected open fun initEvent(binding: VB) {}
    protected open fun initData(viewModel: VM) {}

    // Clean up binding reference to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
