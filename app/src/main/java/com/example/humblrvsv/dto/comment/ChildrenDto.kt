package com.example.humblrvsv.dto.comment


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChildrenDto(
    val kind: String,
    val data: CommentDataDto
)