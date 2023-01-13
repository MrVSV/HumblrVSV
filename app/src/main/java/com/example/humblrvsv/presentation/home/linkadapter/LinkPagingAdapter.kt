package com.example.humblrvsv.presentation.home.linkadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.humblrvsv.databinding.ViewHolderLinkBinding
import com.example.humblrvsv.databinding.ViewHolderSubredditBinding
import com.example.humblrvsv.domain.model.Link
import com.example.humblrvsv.domain.model.Subreddit

class LinkPagingAdapter(
    private val onClick: (Link) -> Unit,
) : PagingDataAdapter<Link, LinkViewHolder>(LinkDiff()) {

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) { link: Link -> onClick(link) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LinkViewHolder(
        ViewHolderLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}