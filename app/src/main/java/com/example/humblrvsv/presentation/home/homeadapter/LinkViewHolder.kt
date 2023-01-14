package com.example.humblrvsv.presentation.home.homeadapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.*
import android.widget.ImageView
import android.widget.TextView
import com.example.humblrvsv.databinding.ViewHolderLinkBinding
import com.example.humblrvsv.domain.model.Link
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.tools.ClickableView
import com.example.humblrvsv.tools.loadImage

class LinkViewHolder(private val binding: ViewHolderLinkBinding) :
    BaseViewHolder<Thing>(binding) {

    override fun bind(item: Thing, onClick: (clickableView: ClickableView, thing: Thing) -> Unit) {
        item as Link

        val adapter by lazy {
            HomePagingAdapter { clickableView, item -> onClick(clickableView, item) }
        }



        binding.btnUpVote.setOnClickListener { onClick(ClickableView.UP_VOTE, item) }
        binding.btnDownVote.setOnClickListener { onClick(ClickableView.DOWN_VOTE, item) }
        binding.btnSave.setOnClickListener { onClick(ClickableView.SAVE, item) }
        binding.postTitle.setOnClickListener { onClick(ClickableView.TITLE, item) }
        binding.btnComment.setOnClickListener { onClick(ClickableView.COMMENT, item) }


        if (item.selfText != "") {
            binding.postBodyText.apply {
                text = item.selfText
                visibility = VISIBLE
            }
        } else binding.postBodyText.visibility = GONE

        if(item.postHint == "image"){
            Log.d(TAG, "bind: ${item.postHint}")
            binding.postBodyImage.apply {
                loadImage(item.url)
                visibility = VISIBLE
            }
        }else binding.postBodyImage.visibility = GONE

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

