package com.example.humblrvsv.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.humblrvsv.R
import com.example.humblrvsv.databinding.ViewHolderCommentBinding
import com.example.humblrvsv.databinding.ViewHolderFriendBinding
import com.example.humblrvsv.databinding.ViewHolderPostTextBinding
import com.example.humblrvsv.domain.model.Friend
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.base.BaseViewHolder
import com.example.humblrvsv.presentation.viewholder.CommentViewHolder
import com.example.humblrvsv.presentation.viewholder.PostTextViewHolder
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.presentation.viewholder.FriendViewHolder

class ThingListAdapter(
    private val onClick: (ClickableView, Thing) -> Unit
) : ListAdapter<Thing, BaseViewHolder<Thing>>(ThingDiff()) {

    override fun onBindViewHolder(holder: BaseViewHolder<Thing>, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) { clickableView, thing -> onClick(clickableView, thing) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Thing> {
        return when(viewType) {
//            FRIEND -> FriendViewHolder(
//                ViewHolderFriendBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//            )
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
            is Friend -> FRIEND
            else -> throw java.lang.IllegalStateException("123")
        }
    }

    companion object {
        const val POST: Int = R.layout.view_holder_post_text
        const val COMMENT: Int = R.layout.view_holder_comment
        const val FRIEND: Int = R.layout.view_holder_friend
    }
}