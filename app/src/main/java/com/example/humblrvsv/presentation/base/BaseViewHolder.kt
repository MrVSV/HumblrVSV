package com.example.humblrvsv.presentation.base

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.humblrvsv.domain.tools.ClickableView

abstract class BaseViewHolder<T : Any>(binding: ViewBinding) :
    ViewHolder(binding.root) {
    abstract fun bind(item: T, onClick: (ClickableView, item: T) -> Unit)

}