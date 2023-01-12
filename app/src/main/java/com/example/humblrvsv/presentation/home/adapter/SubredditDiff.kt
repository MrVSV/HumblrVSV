package com.example.humblrvsv.presentation.home.adapter


import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.humblrvsv.domain.model.Subreddit

class SubredditDiff : DiffUtil.ItemCallback<Subreddit>() {

    override fun areItemsTheSame(oldItem: Subreddit, newItem: Subreddit) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Subreddit, newItem: Subreddit) =
        oldItem == newItem
}