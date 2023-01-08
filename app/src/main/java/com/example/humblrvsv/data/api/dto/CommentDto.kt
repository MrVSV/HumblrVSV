package com.example.humblrvsv.data.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentDto(
    val replies: String?,
    @Json(name = "parent_id")
    val parentId: String?,
    val body: String?
)
