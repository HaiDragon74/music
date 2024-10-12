package com.haidragon.musicapp.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.haidragon.musicapp.presentation.ui.fragment.FavouriteFragment
import com.remotetechs.musicapp.presentation.ui.fragment.PlaylistFragment
import com.haidragon.musicapp.presentation.ui.fragment.SongFragment

class ViewPagerHoneAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SongFragment()
            1 -> PlaylistFragment()
            2 -> SongFragment()
            3 -> FavouriteFragment()
            4 -> SongFragment()
            else -> FavouriteFragment()
        }
    }
}