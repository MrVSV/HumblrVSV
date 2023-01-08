package com.example.humblrvsv.data.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class DataDto(
    //Listing


    //more
    val count: Int?,
    @Json(name = "children")
    val moreComments: List<String>?,

    //Subreddit
    @Json(name = "header_img")
    val headerImg: String?,
    @Json(name = "title")
    val subredditTitle: String?,
    @Json(name = "display_name_prefixed")
    val displayNamePrefixed: String?,
    val subscribers: Int?,
    val name: String?,
    @Json(name = "public_description")
    val publicDescription: String?,
    @Json(name = "community_icon")
    val communityIcon: String?,
    @Json(name = "banner_background_image")
    val bannerBackgroundImage: String?,
    val created: Double?,
    val id: String?,
    val over18: Boolean?,
    val url: String?,

    //Link

    val thumbnail: String?,
    val likes: Boolean?,
    @Json(name = "media_only")
    val mediaOnly: Boolean?,
    @Json(name = "can_gild")
    val canGild: Boolean?,
    val spoiler: Boolean?,
    val locked: Boolean?,
    @Json(name = "subreddit_id")
    val subredditId: String?,
    val author: String?,
    @Json(name = "num_comments")
    val numComments: Int?,
    val permalink: String?,
    @Json(name = "subreddit_subscribers")
    val subredditSubscribers: Int?,
    val media: MediaDto?,
    @Json(name = "is_video")
    val isVideo: Boolean?,

    //Comment
    val replies: String?,
    @Json(name = "parent_id")
    val parentId: String?,
    val body: String?,


    )