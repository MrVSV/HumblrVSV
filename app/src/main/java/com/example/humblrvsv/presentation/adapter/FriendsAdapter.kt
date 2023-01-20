package com.example.humblrvsv.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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

class FriendsAdapter(
    private val onClick: (ClickableView, Friend) -> Unit,
) : RecyclerView.Adapter<BaseViewHolder<Friend>>() {

    private var friendList: List<Friend> = emptyList()

    fun setData(friendList: List<Friend>){
        this.friendList = friendList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Friend>, position: Int) {
        val item = friendList[position]
        holder.bind(item) { clickableView, friend -> onClick(clickableView, friend) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Friend> {
        return FriendViewHolder(
            ViewHolderFriendBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = friendList.size
}