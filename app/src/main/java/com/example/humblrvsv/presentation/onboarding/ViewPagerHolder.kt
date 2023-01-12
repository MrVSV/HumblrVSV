package com.example.humblrvsv.presentation.onboarding

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.humblrvsv.databinding.ItemViewpagerBinding

class ViewPagerHolder(private val binding: ItemViewpagerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(): ImageView {
        return binding.imageView
    }
}