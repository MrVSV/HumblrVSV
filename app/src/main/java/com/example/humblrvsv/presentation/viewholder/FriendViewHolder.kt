package com.example.humblrvsv.presentation.viewholder

import com.example.humblrvsv.R
import com.example.humblrvsv.databinding.ViewHolderFriendBinding
import com.example.humblrvsv.databinding.ViewHolderPostTextBinding
import com.example.humblrvsv.domain.model.Friend
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.presentation.base.BaseViewHolder

class FriendViewHolder(private val binding: ViewHolderFriendBinding) :
    BaseViewHolder<Friend>(binding) {

    override fun bind(item: Friend, onClick: (ClickableView, item: Friend) -> Unit) {

        binding.friendId.text = itemView.context.getString(R.string.user_id, item.id)
        binding.friendName.text = item.name
        binding.root.setOnClickListener { onClick(ClickableView.FRIEND, item) }
    }
}