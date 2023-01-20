package com.example.humblrvsv.presentation.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.humblrvsv.databinding.ItemViewpagerBinding

class ViewPagerAdapter(
    private val allOnboardingHeaders: Array<String>,
    private val allOnboardingTexts: Array<String>,
    private val allOnboardingImages: Array<Int>
) : RecyclerView.Adapter<ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(
            ItemViewpagerBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(
            allOnboardingHeaders[position],
            allOnboardingTexts[position],
            allOnboardingImages[position]
        )
    }

    override fun getItemCount(): Int = allOnboardingImages.size
}
