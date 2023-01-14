package com.example.humblrvsv.presentation.home.homeadapter

import com.example.humblrvsv.databinding.ViewHolderLinkBinding
import com.example.humblrvsv.domain.model.Link
import com.example.humblrvsv.domain.model.Thing

class LinkViewHolder(private val binding: ViewHolderLinkBinding) :
    BaseViewHolder<Thing>(binding) {

    override fun bind(item: Thing, onClick: (item: Thing) -> Unit) {
        item as Link
        binding.root.setOnClickListener { onClick(item) }
        binding.postTitle.text = item.title

        val score = item.score/1000
        if(item.score > 999) binding.likes.text = "${score}K"
        else binding.likes.text = item.score.toString()

        val comments = item.numComments/1000
        if(item.numComments > 999) binding.commentsCount.text = "${comments}K"
        else binding.commentsCount.text = item.numComments.toString()

        binding.subredditName.text = item.subredditNamePrefixed
        binding.userName.text = "u/${item.author}"
    }
}

