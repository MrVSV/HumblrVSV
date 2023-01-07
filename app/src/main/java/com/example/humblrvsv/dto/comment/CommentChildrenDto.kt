package com.example.humblrvsv.dto.comment


import com.example.humblrvsv.dto.DataDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentChildrenDto(
    val kind: String,
    val `data`: DataDto
)