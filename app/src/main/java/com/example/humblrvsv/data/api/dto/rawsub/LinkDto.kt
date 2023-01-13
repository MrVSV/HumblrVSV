package com.example.humblrvsv.data.api.dto.rawsub


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkDto(
    val kind: String,
    val data: LinkDataDto
)