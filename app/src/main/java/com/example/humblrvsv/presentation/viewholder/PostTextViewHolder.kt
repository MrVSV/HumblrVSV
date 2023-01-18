package com.example.humblrvsv.presentation.viewholder

import android.view.ViewGroup.GONE
import android.view.ViewGroup.VISIBLE
import com.example.humblrvsv.databinding.ViewHolderPostTextBinding
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.base.BaseViewHolder
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.domain.tools.loadImage

class PostTextViewHolder(private val binding: ViewHolderPostTextBinding) :
    BaseViewHolder<Thing>(binding) {

    override fun bind(item: Thing, onClick: (clickableView: ClickableView, thing: Thing) -> Unit) {
        item as Post

        var likedByUser = item.likedByUser
        val score = item.score

        showScore(item.score)

        binding.btnUpVote.isSelected = item.likedByUser == true

        binding.btnUpVote.setOnClickListener {
            onClick(ClickableView.UP_VOTE, item)
            binding.btnUpVote.isSelected = !binding.btnUpVote.isSelected
            binding.btnDownVote.isSelected = false
            showScore(item.score)
        }

        binding.btnDownVote.isSelected = item.likedByUser == false
        binding.btnDownVote.setOnClickListener {
            onClick(ClickableView.DOWN_VOTE, item)
            binding.btnDownVote.isSelected = !binding.btnDownVote.isSelected
            binding.btnUpVote.isSelected = false
            showScore(item.score)
        }

        binding.btnSave.setOnClickListener {
            onClick(ClickableView.SAVE, item)
        binding.btnSave.isSelected =! binding.btnSave.isSelected
        }

        binding.postTitle.setOnClickListener { onClick(ClickableView.POST_TITLE, item) }
        binding.btnComment.setOnClickListener { onClick(ClickableView.COMMENT, item) }

        if (item.postHint == "image") {
            binding.postBodyImage.apply {
                loadImage(item.url)
                visibility = VISIBLE
            }
        } else binding.postBodyImage.visibility = GONE

        if (item.selfText != "") {
            binding.postBodyText.apply {
                text = item.selfText
                visibility = VISIBLE
            }
        } else binding.postBodyText.visibility = GONE

        binding.postTitle.text = item.title

        val comments = item.numComments / 1000
        if (item.numComments > 999) binding.commentsCount.text = "${comments}K"
        else binding.commentsCount.text = item.numComments.toString()

        binding.subredditName.text = item.subredditNamePrefixed
        binding.userName.text = "u/${item.author}"


    }
    private fun showScore(score: Int){
        if (score > 999) binding.likes.text = "${score / 1000}k"
        else binding.likes.text = "$score"
    }
}

