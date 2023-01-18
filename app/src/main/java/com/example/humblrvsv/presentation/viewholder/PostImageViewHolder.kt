package com.example.humblrvsv.presentation.viewholder

import android.content.ContentValues
import android.util.Log
import android.view.View
import com.example.humblrvsv.databinding.ViewHolderPostImageBinding
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.base.BaseViewHolder
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.domain.tools.loadImage

class PostImageViewHolder (private val binding: ViewHolderPostImageBinding) :
    BaseViewHolder<Thing>(binding) {

    override fun bind(item: Thing, onClick: (clickableView: ClickableView, thing: Thing) -> Unit) {
        item as Post

        binding.btnUpVote.setOnClickListener { onClick(ClickableView.UP_VOTE, item) }
        binding.btnDownVote.setOnClickListener { onClick(ClickableView.DOWN_VOTE, item) }
        binding.btnSave.setOnClickListener { onClick(ClickableView.SAVE, item) }
        binding.postTitle.setOnClickListener { onClick(ClickableView.POST_TITLE, item) }
        binding.btnComment.setOnClickListener { onClick(ClickableView.COMMENT, item) }

        if(item.postHint == "image"){
            Log.d(ContentValues.TAG, "bind: ${item.postHint}")
            binding.postBodyImage.apply {
                loadImage(item.url)
                visibility = View.VISIBLE
            }
        }else binding.postBodyImage.visibility = View.GONE

        binding.postTitle.text = item.title

        val score = item.score / 1000
        if (item.score > 999) binding.likes.text = "${score}K"
        else binding.likes.text = item.score.toString()

        val comments = item.numComments / 1000
        if (item.numComments > 999) binding.commentsCount.text = "${comments}K"
        else binding.commentsCount.text = item.numComments.toString()

        binding.subredditName.text = item.subredditNamePrefixed
        binding.userName.text = "u/${item.author}"
    }
}