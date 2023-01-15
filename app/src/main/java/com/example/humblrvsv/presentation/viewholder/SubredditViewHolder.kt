package com.example.humblrvsv.presentation.viewholder

import com.example.humblrvsv.databinding.ViewHolderSubredditBinding
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.base.BaseViewHolder
import com.example.humblrvsv.tools.ClickableView


class SubredditViewHolder(private val binding: ViewHolderSubredditBinding) :
    BaseViewHolder<Thing>(binding) {

    override fun bind(item: Thing, onClick: (clickableView: ClickableView, thing: Thing) -> Unit) {
        item as Subreddit
//        binding.root.setOnClickListener { onClick(item) }
        binding.subredditName.text = item.namePrefixed
        binding.subredditDescription.text = item.description
    }
}

