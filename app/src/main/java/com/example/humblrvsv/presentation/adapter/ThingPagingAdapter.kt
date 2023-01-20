package com.example.humblrvsv.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.humblrvsv.R
import com.example.humblrvsv.databinding.ViewHolderPostTextBinding
import com.example.humblrvsv.databinding.ViewHolderSubredditBinding

import com.example.humblrvsv.domain.model.*
import com.example.humblrvsv.presentation.base.BaseViewHolder
import com.example.humblrvsv.presentation.viewholder.PostTextViewHolder
import com.example.humblrvsv.presentation.viewholder.SubredditViewHolder
import com.example.humblrvsv.domain.tools.ClickableView

class ThingPagingAdapter(
    private val onClick: (ClickableView, Thing) -> Unit,
) : PagingDataAdapter<Thing, BaseViewHolder<Thing>>(ThingDiff()) {

    override fun onBindViewHolder(holder: BaseViewHolder<Thing>, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) { clickableView,thing -> onClick(clickableView, thing) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Thing> {
        return when (viewType) {
            SUBREDDIT -> SubredditViewHolder(
                ViewHolderSubredditBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false)
            )
            POST_TEXT -> PostTextViewHolder(
                ViewHolderPostTextBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false)
            )
//            LINK_IMAGE -> PostImageViewHolder(
//                ViewHolderLinkImageBinding.inflate(LayoutInflater.from(parent.context),
//                    parent,
//                    false)
//            )
            else -> throw java.lang.IllegalStateException("456")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Subreddit -> SUBREDDIT
            is Post -> POST_TEXT
//            is LinkImage -> LINK_IMAGE
            else -> throw java.lang.IllegalStateException("123")
        }
    }

    companion object {
        const val SUBREDDIT: Int = R.layout.view_holder_subreddit
        const val POST_TEXT: Int = R.layout.view_holder_post_text
//        const val LINK_IMAGE: Int = 3
    }
}