package com.example.humblrvsv.dto.link


import com.example.humblrvsv.dto.MediaDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkDataDto(
    val subreddit: String,
    @Json(name = "author_fullname")
    val authorFullName: String,
    val saved: Boolean,
    val clicked: Boolean,
    val title: String,
    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    val hidden: Boolean,
    val name: String,
    val score: Int,
    val thumbnail: String,
    @Json(name = "allow_live_comments")
    val allowLiveComments: Boolean,
    val likes: Boolean?,
    @Json(name = "over_18")
    val over18: Boolean,
    @Json(name = "media_only")
    val mediaOnly: Boolean,
    @Json(name = "can_gild")
    val canGild: Boolean,
    val spoiler: Boolean,
    val locked: Boolean,
    @Json(name = "subreddit_id")
    val subredditId: String,
    val id: String,
    val author: String,
    @Json(name = "num_comments")
    val numComments: Int,
    val permalink: String,
    @Json(name = "subreddit_subscribers")
    val subredditSubscribers: Int,
    val media: MediaDto,
    @Json(name = "is_video")
    val isVideo: Boolean
)