package com.example.humblrvsv.dto.subreddit


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditDataDto(
    @Json(name = "header_img")
    val headerImg: String?,
    val title: String,
    @Json(name = "display_name_prefixed")
    val displayNamePrefixed: String,
    val subscribers: Int,
    val name: String,
    @Json(name = "public_description")
    val publicDescription: String,
    @Json(name = "community_icon")
    val communityIcon: String,
    @Json(name = "banner_background_image")
    val bannerBackgroundImage: String,
    val created: Double,
    val id: String,
    val over18: Boolean,
    val url: String
)