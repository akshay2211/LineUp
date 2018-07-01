package com.fxn.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.fxn.fragments.ColorBackgroundFragment

/**
 * Created by akshay on 25/06/18.
 */
class ColorAdapter(fragmentManager: FragmentManager, private val count: Int) :
        FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return ColorBackgroundFragment.newInstance(position, "")
    }

    // 3
    override fun getCount(): Int {
        return count
    }
}