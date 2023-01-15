package com.example.humblrvsv.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.humblrvsv.R
import com.example.humblrvsv.databinding.FragmentOnBoardingBinding
import com.example.humblrvsv.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentOnBoardingBinding.inflate(inflater)

    private val images = listOf(
        R.drawable.img_onboarding_first,
        R.drawable.img_onboarding_second,
        R.drawable.img_onboarding_third
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = ViewPagerAdapter(images)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {_,_ -> }.attach()
    }

}