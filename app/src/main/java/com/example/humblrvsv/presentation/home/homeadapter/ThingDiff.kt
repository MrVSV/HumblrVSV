package com.example.humblrvsv.presentation.home.homeadapter


import androidx.recyclerview.widget.DiffUtil
import com.example.humblrvsv.domain.model.Thing

class ThingDiff : DiffUtil.ItemCallback<Thing>() {

    override fun areItemsTheSame(oldItem: Thing, newItem: Thing) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Thing, newItem: Thing) =
        oldItem.id == newItem.id
}