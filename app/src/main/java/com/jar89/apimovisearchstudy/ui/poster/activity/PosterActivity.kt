package com.jar89.apimovisearchstudy.ui.poster.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.jar89.apimovisearchstudy.R
import com.jar89.apimovisearchstudy.databinding.ActivityDetailsBinding
import com.jar89.apimovisearchstudy.ui.poster.fragmenst.DetailsAdapter

class PosterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val poster = intent.getStringExtra("poster") ?: ""
        val movieId = intent.getStringExtra("id") ?: ""

        binding.viewPager.adapter = DetailsAdapter(
            supportFragmentManager,
            lifecycle, poster, movieId
        )

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.poster)
                1 -> tab.text = getString(R.string.details)
            }
        }
        tabMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }
}