package com.example.humblrvsv.presentation.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.humblrvsv.databinding.ItemViewpagerBinding

class ViewPagerAdapter(
    private val onBoardingImages: List<Int>,
//    val onBoardingTitles: Array<String>,
//    val onBoardingTexts: Array<String>,
) : RecyclerView.Adapter<ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(
            ItemViewpagerBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val image = onBoardingImages[position]
        holder.bind().setImageResource(image)
    }

    override fun getItemCount(): Int {
        return onBoardingImages.size
    }
}
