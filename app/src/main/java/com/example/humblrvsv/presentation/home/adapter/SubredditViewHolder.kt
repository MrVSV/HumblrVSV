package com.example.humblrvsv.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.humblrvsv.databinding.ViewHolderSubredditBinding
import com.example.humblrvsv.domain.model.Subreddit


class SubredditViewHolder(private val binding: ViewHolderSubredditBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Subreddit, onClick: (item: Subreddit) -> Unit) {

        binding.root.setOnClickListener {
            onClick(item)
        }

        binding.subredditName.text = item.namePrefixed
        binding.subredditDescription.text = item.description

    }
}

