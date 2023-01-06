package com.example.humblrvsv.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaDto(
    @Json(name = "reddit_video")
    val redditVideo: RedditVideoDto
)