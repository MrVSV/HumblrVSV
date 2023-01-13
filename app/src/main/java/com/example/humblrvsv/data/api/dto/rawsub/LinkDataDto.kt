package com.example.humblrvsv.data.api.dto.rawsub


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkDataDto(
    val subreddit: String,
    @Json(name = "selftext")
    val selfText: String,
    @Json(name = "author_fullname")
    val authorFullname: String,
    val saved: Boolean,
    val title: String,
    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    val name: String,
    val score: Int,
    val thumbnail: String?,
    @Json(name = "post_hint")
    val postHint: String?,
    val created: Double,
    @Json(name = "url_overridden_by_dest")
    val urlOverriddenByDest: String,
    @Json(name = "subreddit_id")
    val subredditId: String,
    val id: String,
    val author: String,
    @Json(name = "num_comments")
    val numComments: Int,
    val permalink: String,
    val url: String,
    val media: Media?,
    @Json(name = "is_video")
    val isVideo: Boolean,
)