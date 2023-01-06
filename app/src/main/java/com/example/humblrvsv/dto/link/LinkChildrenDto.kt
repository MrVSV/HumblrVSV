package com.example.humblrvsv.dto.link


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkChildrenDto(
    val kind: String,
    @Json(name = "data")
    val linkData: LinkDataDto
)