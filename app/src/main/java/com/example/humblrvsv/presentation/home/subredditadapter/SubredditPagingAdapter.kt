package com.example.humblrvsv.presentation.home.subredditadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.humblrvsv.databinding.ViewHolderSubredditBinding
import com.example.humblrvsv.domain.model.Subreddit

class SubredditPagingAdapter(
    private val onClick: (Subreddit) -> Unit,
) : PagingDataAdapter<Subreddit, SubredditViewHolder>(SubredditDiff()) {

    override fun onBindViewHolder(holder: SubredditViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) { subreddit: Subreddit -> onClick(subreddit) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubredditViewHolder(
        ViewHolderSubredditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}