package com.example.humblrvsv.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Kind(
    val kind: String,
    val data: DataDto
)
