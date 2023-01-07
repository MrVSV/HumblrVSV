package com.example.humblrvsv.dto


import com.example.humblrvsv.dto.comment.ChildrenDto
import com.example.humblrvsv.dto.comment.RepliesDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataDto(
    //Listing
    val after: Any?,
    val dist: Any?,
    val modhash: Any?,
    @Json(name = "geo_filter")
    val geoFilter: String,
    val children: List<Kind>,
    val before: Any?,

    //Subreddit
    @Json(name = "header_img")
    val headerImg: String?,
    @Json(name = "title")
    val subredditTitle: String,
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
    val url: String,

    //Link
    val subreddit: String,
    @Json(name = "author_fullname")
    val authorFullName: String,
    val saved: Boolean,
    val clicked: Boolean,
//    val title: String,
    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    val hidden: Boolean,
//    val name: String,
    val score: Int,
    val thumbnail: String,
    val likes: Boolean?,
    @Json(name = "media_only")
    val mediaOnly: Boolean,
    @Json(name = "can_gild")
    val canGild: Boolean,
    val spoiler: Boolean,
    val locked: Boolean,
    @Json(name = "subreddit_id")
    val subredditId: String,
    val author: String,
    @Json(name = "num_comments")
    val numComments: Int,
    val permalink: String,
    @Json(name = "subreddit_subscribers")
    val subredditSubscribers: Int,
    val media: MediaDto,
    @Json(name = "is_video")
    val isVideo: Boolean,

    //Comment
    val replies: RepliesDto?,
    @Json(name = "parent_id")
    val parentId: String,
    val body: String?,

    //more
    val count: Int?,
//    @Json(name = "children")
//    val moreComments: List<String>?
)