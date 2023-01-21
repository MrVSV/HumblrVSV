package com.example.humblrvsv.presentation.viewholder

import com.example.humblrvsv.databinding.ViewHolderCommentBinding
import com.example.humblrvsv.domain.model.Comment
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.ClickableView
import com.example.humblrvsv.presentation.base.BaseViewHolder

class CommentViewHolder(private val binding: ViewHolderCommentBinding) :
    BaseViewHolder<Thing>(binding) {

//    private val adapter by lazy {
//        ThingPagingAdapter { clickableView, item -> onClick(clickableView, item) }
//    }
//
//    private fun onClick(clickableView: ClickableView, item: Thing) {}

    override fun bind(item: Thing, onClick: (ClickableView, item: Thing) -> Unit) {
        item as Comment

        binding.commentBodyText.text = item.body
        binding.userName.text = "u/${item.author}"
        binding.likes.text = item.score.toString()
//        binding.root.setOnClickListener {
//            onClick(ClickableView.COMMENT, item)
//            if (binding.recyclerChild.visibility == View.GONE)
//                binding.recyclerChild.visibility = View.VISIBLE
//            else if (binding.recyclerChild.visibility == View.VISIBLE)
//                binding.recyclerChild.visibility = View.GONE
//        }
//        binding.recyclerChild.adapter = adapter
    }
}