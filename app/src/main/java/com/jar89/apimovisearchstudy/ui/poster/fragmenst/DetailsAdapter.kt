package com.jar89.apimovisearchstudy.ui.poster.fragmenst

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailsAdapter(
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle,
    private val posterUrl: String,
    private val movieId: String
) :
    FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PosterFragment.newInstance(posterUrl)
            else -> AboutFragment.newInstance(movieId)
        }
    }
}

