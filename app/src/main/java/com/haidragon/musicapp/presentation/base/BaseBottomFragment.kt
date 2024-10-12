package com.haidragon.musicapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomFragment<viewBinding: ViewBinding> : BottomSheetDialogFragment() {
    lateinit var binding:viewBinding
    abstract fun getViewBinding(inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?):viewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = getViewBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupView()
        setupViewModel()
        setupListen()
        observeViewModel()
    }

    open fun initView() {
        //MappingUI
    }

    open fun setupView() {
        //MappingUI
    }

    open fun setupListen() {
        //handle
    }

    open fun setupViewModel() {
        //setupViewModel
    }

    open fun observeViewModel() {
        //observeViewModel
    }
}