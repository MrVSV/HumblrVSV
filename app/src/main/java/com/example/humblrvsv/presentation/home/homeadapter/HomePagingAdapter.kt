package com.example.humblrvsv.presentation.home.homeadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.humblrvsv.databinding.ViewHolderLinkBinding
import com.example.humblrvsv.databinding.ViewHolderSubredditBinding
import com.example.humblrvsv.domain.model.Link
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.model.Thing

class HomePagingAdapter(
    private val onClick: (Thing) -> Unit,
) : PagingDataAdapter<Thing, BaseViewHolder<Thing>>(ThingDiff()) {

    override fun onBindViewHolder(holder: BaseViewHolder<Thing>, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) { thing -> onClick(thing) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Thing> {
        return when (viewType) {
            SUBREDDIT -> SubredditViewHolder(
                ViewHolderSubredditBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false)
            )
            LINK -> LinkViewHolder(
                ViewHolderLinkBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false)
            )
            else -> throw java.lang.IllegalStateException("456")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Subreddit -> SUBREDDIT
            is Link -> LINK
            else -> throw java.lang.IllegalStateException("123")
        }
    }

    companion object {
        const val SUBREDDIT: Int = 1
        const val LINK: Int = 2
    }
}