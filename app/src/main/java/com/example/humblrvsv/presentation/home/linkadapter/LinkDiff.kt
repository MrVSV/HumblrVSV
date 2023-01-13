package com.example.humblrvsv.presentation.home.linkadapter


import androidx.recyclerview.widget.DiffUtil
import com.example.humblrvsv.domain.model.Link
import com.example.humblrvsv.domain.model.Subreddit

class LinkDiff : DiffUtil.ItemCallback<Link>() {

    override fun areItemsTheSame(oldItem: Link, newItem: Link) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Link, newItem: Link) =
        oldItem == newItem
}