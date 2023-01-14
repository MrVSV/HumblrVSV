package com.example.humblrvsv.tools

import com.example.humblrvsv.data.api.dto.linkdto.LinkDto
import com.example.humblrvsv.data.api.dto.subredditdto.SubredditDto
import com.example.humblrvsv.domain.model.Link
import com.example.humblrvsv.domain.model.Subreddit
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

fun List<SubredditDto>.toListSubreddit(): List<Subreddit> {
    val newList = mutableListOf<Subreddit>()
    this.forEach { item ->
        newList.add(item.toSubreddit())
    }
    return newList
}

fun List<LinkDto>.toListLink(): List<Link> {
    val newList = mutableListOf<Link>()
    this.forEach { item ->
        newList.add(item.toLink())
    }
    return newList
}

fun TabLayout.setSelectedTabListener(block: (position: Int) -> Unit){
    this.addOnTabSelectedListener(object : OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if (tab != null) { block(tab.position) }
        }
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}