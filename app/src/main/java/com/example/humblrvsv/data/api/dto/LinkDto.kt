package com.example.humblrvsv.data.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkDto(
    val subreddit: String?,
    @Json(name = "author_fullname")
    val authorFullName: String?,
    val saved: Boolean?,
    val clicked: Boolean?,
    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String?,
    val hidden: Boolean?,
    val score: Int?
)