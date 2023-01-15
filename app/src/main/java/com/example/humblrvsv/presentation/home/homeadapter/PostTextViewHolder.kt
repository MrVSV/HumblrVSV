package com.example.humblrvsv.presentation.home.homeadapter

import android.view.ViewGroup.GONE
import android.view.ViewGroup.VISIBLE
import com.example.humblrvsv.databinding.ViewHolderPostTextBinding
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.base.BaseViewHolder
import com.example.humblrvsv.tools.ClickableView
import com.example.humblrvsv.tools.loadImage

class PostTextViewHolder(private val binding: ViewHolderPostTextBinding) :
    BaseViewHolder<Thing>(binding) {

    override fun bind(item: Thing, onClick: (clickableView: ClickableView, thing: Thing) -> Unit) {
        item as Post

        var likedByUser = item.likedByUser
        var score = item.score

        showScore(score)

        when (item.likedByUser) {
            true -> binding.subredditName.text = "true"
            false -> binding.subredditName.text = "false"
            null -> binding.subredditName.text = "null"
        }
        binding.btnUpVote.isSelected = likedByUser == true

        binding.btnUpVote.setOnClickListener {

            onClick(ClickableView.UP_VOTE, item)
            binding.btnUpVote.isSelected = !binding.btnUpVote.isSelected
            binding.btnDownVote.isSelected = false
//            when (likedByUser) {
//                null -> {
//                    likedByUser = true
//                    score += 1
//                }
//                true -> {
//                    likedByUser = null
//                    score -= 1
//                }
//                false -> {
//                    likedByUser = true
//                    score += 2
//                }
//            }
//            showScore(score)
        }

        binding.btnDownVote.isSelected = likedByUser == false
        binding.btnDownVote.setOnClickListener {

            onClick(ClickableView.DOWN_VOTE, item)
            binding.btnDownVote.isSelected = !binding.btnDownVote.isSelected
            binding.btnUpVote.isSelected = false
//            when (likedByUser) {
//                null -> {
//                    likedByUser = false
//                    score -= 1
//                }
//                true -> {
//                    likedByUser = false
//                    score -= 2
//                }
//                false -> {
//                    likedByUser = null
//                    score += 1
//                }
//            }
//            showScore(score)
        }
        binding.btnSave.setOnClickListener { onClick(ClickableView.SAVE, item) }
        binding.postTitle.setOnClickListener { onClick(ClickableView.TITLE, item) }
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

