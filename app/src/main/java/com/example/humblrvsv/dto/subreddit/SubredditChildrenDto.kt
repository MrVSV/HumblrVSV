package com.example.humblrvsv.dto.subreddit


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditChildrenDto(
    val kind: String,
    @Json(name = "data")
    val subredditData: SubredditDataDto
)