package com.example.humblrvsv.presentation.home.homeadapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.humblrvsv.tools.ClickableView

abstract class BaseViewHolder<T : Any>(private val binding: ViewBinding) :
    ViewHolder(binding.root) {
    abstract fun bind(item: T, onClick: (ClickableView, item: T) -> Unit)

}