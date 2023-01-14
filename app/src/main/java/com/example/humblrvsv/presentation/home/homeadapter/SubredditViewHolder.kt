package com.example.humblrvsv.presentation.home.homeadapter

import com.example.humblrvsv.databinding.ViewHolderSubredditBinding
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.model.Thing


class SubredditViewHolder(private val binding: ViewHolderSubredditBinding) :
    BaseViewHolder<Thing>(binding) {

    override fun bind(item: Thing, onClick: (item: Thing) -> Unit) {
        item as Subreddit
        binding.root.setOnClickListener { onClick(item) }
        binding.subredditName.text = item.namePrefixed
        binding.subredditDescription.text = item.description
    }
}

