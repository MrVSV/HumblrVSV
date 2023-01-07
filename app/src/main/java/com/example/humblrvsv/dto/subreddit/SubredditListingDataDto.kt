package com.example.humblrvsv.dto.subreddit


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditListingDataDto(
    val after: String,
    val children: List<SubredditChildrenDto>,
    val before: String?
)