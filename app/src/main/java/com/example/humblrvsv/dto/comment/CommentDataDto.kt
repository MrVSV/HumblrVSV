package com.example.humblrvsv.dto.comment

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CommentDataDto(
    @Json(name = "subreddit_id")
    val subredditId: String?,
    val likes: Boolean?,
    val replies: RepliesDto?,
    val saved: Boolean?,
    val id: String,
    @Json(name = "parent_id")
    val parentId: String,
    val score: Int?,
    @Json(name = "author_fullname")
    val authorFullname: String?,
    val body: String?,
    val name: String,
    val permalink: String?,
    val created: Double?,
    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String?,
    val count: Int?,
    val children: List<String>?
)

