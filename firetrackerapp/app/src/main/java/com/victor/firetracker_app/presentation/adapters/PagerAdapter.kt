package com.victor.firetracker_app.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.victor.firetracker_app.presentation.fragments.AboutFragment
import com.victor.firetracker_app.presentation.fragments.ConfigFragment
import com.victor.firetracker_app.presentation.fragments.HomeFragment

class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ConfigFragment()
            2 -> AboutFragment()
            else -> throw Exception("ViewPager Error.")
        }
    }
}