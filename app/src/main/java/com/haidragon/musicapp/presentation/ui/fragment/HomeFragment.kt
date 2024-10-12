package com.haidragon.musicapp.presentation.ui.fragment

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.haidragon.musicapp.R
import com.haidragon.musicapp.databinding.FragmentHomeBinding
import com.haidragon.musicapp.presentation.ui.activity.HomeActivity
import com.haidragon.musicapp.presentation.ui.adapter.ViewPagerHoneAdapter
import com.haidragon.musicapp.presentation.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var viewPagerHoneAdapter: ViewPagerHoneAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabMusic: TabLayout
    private lateinit var mContent: Context
    companion object{

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContent = context
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun setUpView() {
        super.setUpView()
        setColorTextApp()
        setupViewPagerAndTabs()
    }

    override fun onResume() {
        super.onResume()
        view?.isEnabled=true
    }

    override fun onPause() {
        super.onPause()
        view?.isEnabled=false
    }

    override fun initUI() {
        super.initUI()
        viewPager = binding.viewPager
        tabMusic = binding.tabMusic

    }
    private fun setColorTextApp() {
        val myText = binding.tvApp.text.toString()
        val width = binding.tvApp.paint.measureText(myText)
        val textShader = LinearGradient(
            0f, 0f, width, binding.tvApp.textSize,
            intArrayOf(
                Color.BLUE,
                Color.DKGRAY
            ),
            null,
            Shader.TileMode.CLAMP
        )
        binding.tvApp.paint.shader = textShader
    }


    private fun setupViewPagerAndTabs() {
        viewPagerHoneAdapter = ViewPagerHoneAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = viewPagerHoneAdapter

        TabLayoutMediator(tabMusic, viewPager) { tab, position ->
            //set color tb
            tab.view.setBackgroundResource(HomeActivity.colorBackground)
            //tabView.setBackgroundColor(ContextCompat.getColor(mContent, R.color.background_app))4
            val typeface: Typeface? = ResourcesCompat.getFont(mContent, R.font.roboto_light)
            val textView = TextView(mContent).apply {
                setTypeface(typeface, Typeface.NORMAL)
                setBackgroundResource(R.drawable.bcr_no_click_tab)
                setTextColor(requireContext().getColor(R.color.white))
                when (position) {
                    0 -> {
                        text = context.getString(R.string.all)
                        setTextColor(ContextCompat.getColor(mContent, R.color.black))
                        setTypeface(typeface, Typeface.BOLD)
                        setBackgroundResource(R.drawable.bcr_click_tab)
                    }

                    1 -> {
                        text = context.getString(R.string.play_list)
                    }

                    2 -> {
                        text = "Songs2"
                    }

                    3 -> {
                        text = "Songs3"
                    }

                    4 -> {
                        text = "Songs4"
                    }

                    else -> text = "Custom"
                }
                gravity = Gravity.CENTER
                textSize = 16f
                setPadding(40,0,40,0)
                val layoutParamsViewGroupItem = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setTextColor(ContextCompat.getColor(mContent, R.color.white))
                layoutParams = layoutParamsViewGroupItem
            }
            //margin tab
            for (i in 0 until tabMusic.tabCount) {
                val tabItem = (tabMusic.getChildAt(0) as ViewGroup).getChildAt(i)
                val layoutParams = tabItem.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(-12, 0, -14, 0)
                tabItem.requestLayout()
            }
            tab.customView = textView
        }.attach()
        tabMusic.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val textView = tab.customView as? TextView
                val typeface: Typeface? = ResourcesCompat.getFont(mContent, R.font.roboto_light)
                textView?.apply {
                    setTypeface(typeface, Typeface.BOLD)
                    setBackgroundResource(R.drawable.bcr_click_tab) // Change to selected background
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val textView = tab.customView as? TextView
                textView?.apply {
                    val typeface: Typeface? = ResourcesCompat.getFont(mContent, R.font.roboto_light)
                    setTypeface(typeface, Typeface.NORMAL)
                    setBackgroundResource(R.drawable.bcr_no_click_tab) // Change to unselected background
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Optional: Handle reselection if needed
            }
        })
    }
}