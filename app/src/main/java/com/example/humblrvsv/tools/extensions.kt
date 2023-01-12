package com.example.humblrvsv.tools

import com.example.humblrvsv.data.api.dto.SubredditDto
import com.example.humblrvsv.domain.model.Subreddit

fun List<SubredditDto>.toListSubreddit(): List<Subreddit> {
    val newList = mutableListOf<Subreddit>()

    this.forEach { item ->
        newList.add(item.toSubreddit())
    }
    return newList
}