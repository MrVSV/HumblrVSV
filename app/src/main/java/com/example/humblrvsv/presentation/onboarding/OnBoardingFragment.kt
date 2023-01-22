package com.example.humblrvsv.presentation.onboarding

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.humblrvsv.ONBOARDING_IS_SHOWN
import com.example.humblrvsv.ONBOARDING_SHARED_NAME
import com.example.humblrvsv.R
import com.example.humblrvsv.databinding.FragmentOnBoardingBinding
import com.example.humblrvsv.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentOnBoardingBinding.inflate(inflater)

    private var mediator: TabLayoutMediator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPager()
        setTabs()
        saveOnboardingShown(createSharedPreference(ONBOARDING_SHARED_NAME))
        setAuthorizeButton()
    }

    private fun saveOnboardingShown(preferences: SharedPreferences) {
        preferences.edit().putBoolean(ONBOARDING_IS_SHOWN, true).apply()
    }

    private fun setViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter(
            resources.getStringArray(R.array.onboarding_texts_array1),
            resources.getStringArray(R.array.onboarding_texts_array2),
            arrayOf(R.drawable.img_onboarding_first,
                R.drawable.img_onboarding_second,
                R.drawable.img_onboarding_third)
        )
    }

    private fun setTabs() {
        mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }
        mediator!!.attach()
    }

    private fun setAuthorizeButton() {
        binding.skipText.setOnClickListener {
            findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToAuthFragment())
        }
    }
}