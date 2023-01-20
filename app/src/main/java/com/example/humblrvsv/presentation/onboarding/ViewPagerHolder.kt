package com.example.humblrvsv.presentation.onboarding

import androidx.recyclerview.widget.RecyclerView
import com.example.humblrvsv.databinding.ItemViewpagerBinding

class ViewPagerHolder(private val binding: ItemViewpagerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(itemHeaders: String, itemTexts: String, itemImages: Int) {
        binding.onBoardingTitle.text = itemHeaders
        binding.onBoardingText.text = itemTexts
        binding.imageView.setImageResource(itemImages)
    }
}