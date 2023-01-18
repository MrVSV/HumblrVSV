package com.example.humblrvsv.presentation.viewholder

import android.view.View
import com.example.humblrvsv.databinding.ViewHolderCommentBinding
import com.example.humblrvsv.domain.model.Comment
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.base.BaseViewHolder
import com.example.humblrvsv.domain.tools.ClickableView

class CommentViewHolder(private val binding: ViewHolderCommentBinding) :
    BaseViewHolder<Thing>(binding) {

    override fun bind(item: Thing, onClick: (ClickableView, item: Thing) -> Unit) {
        item as Comment

        binding.commentBodyText.text = item.body
        binding.root.setOnClickListener {
            onClick(ClickableView.COMMENT, item)
            if (binding.recyclerChild.visibility == View.GONE)
                binding.recyclerChild.visibility = View.VISIBLE
            else if (binding.recyclerChild.visibility == View.VISIBLE)
                binding.recyclerChild.visibility = View.GONE
        }
    }
}