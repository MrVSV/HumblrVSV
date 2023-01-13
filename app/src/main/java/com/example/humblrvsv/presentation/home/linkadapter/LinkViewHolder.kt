package com.example.humblrvsv.presentation.home.linkadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.humblrvsv.databinding.ViewHolderLinkBinding
import com.example.humblrvsv.databinding.ViewHolderSubredditBinding
import com.example.humblrvsv.domain.model.Link
import com.example.humblrvsv.domain.model.Subreddit

class LinkViewHolder(private val binding: ViewHolderLinkBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Link, onClick: (item: Link) -> Unit) {
        binding.root.setOnClickListener { onClick(item) }
        binding.linkName.text = item.title
    }
}

