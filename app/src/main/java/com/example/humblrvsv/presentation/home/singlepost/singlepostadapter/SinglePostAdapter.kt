package com.example.humblrvsv.presentation.home.singlepost.singlepostadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.humblrvsv.databinding.ViewHolderCommentBinding
import com.example.humblrvsv.databinding.ViewHolderPostTextBinding
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.base.BaseViewHolder
import com.example.humblrvsv.presentation.home.homeadapter.HomePagingAdapter
import com.example.humblrvsv.presentation.home.homeadapter.ThingDiff
import com.example.humblrvsv.presentation.viewholder.CommentViewHolder
import com.example.humblrvsv.presentation.viewholder.PostTextViewHolder
import com.example.humblrvsv.domain.tools.ClickableView

class SinglePostAdapter(
    private val onClick: (ClickableView, Thing) -> Unit
) : ListAdapter<Thing, BaseViewHolder<Thing>>(ThingDiff()) {

    override fun onBindViewHolder(holder: BaseViewHolder<Thing>, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) { clickableView, thing -> onClick(clickableView, thing) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Thing> {
        return when(viewType) {
            COMMENT -> CommentViewHolder(
                ViewHolderCommentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            POST -> PostTextViewHolder(
                ViewHolderPostTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw java.lang.IllegalStateException("456")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Subreddit -> HomePagingAdapter.SUBREDDIT
            is Post -> HomePagingAdapter.POST_TEXT
//            is LinkImage -> LINK_IMAGE
            else -> throw java.lang.IllegalStateException("123")
        }
    }

    companion object {
        const val POST: Int = 1
        const val COMMENT: Int = 2
//        const val LINK_IMAGE: Int = 3
    }
}