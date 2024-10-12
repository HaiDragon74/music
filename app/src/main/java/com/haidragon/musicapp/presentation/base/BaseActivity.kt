package com.haidragon.musicapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<viewBinding: ViewBinding>  : AppCompatActivity() {
    lateinit var binding:viewBinding
    abstract fun getActivityViewBinding(inflater: LayoutInflater): viewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=getActivityViewBinding(layoutInflater)
        setContentView(binding.root)
        initUI()
        setupView()
        setupListen()
        setupViewModel()
        observeViewModel()
    }
    open fun initUI() {
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