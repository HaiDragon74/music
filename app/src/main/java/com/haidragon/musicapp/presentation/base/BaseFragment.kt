package com.haidragon.musicapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB:ViewBinding> : Fragment() {
    lateinit var binding:VB
    abstract fun getViewBinding(inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?):VB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater, container, savedInstanceState)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setUpView()
        setupListen()
        observeViewModel()
        setupViewModel()
    }
    open fun initUI() {
        //MappingUI
    }
    open fun setUpView() {
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