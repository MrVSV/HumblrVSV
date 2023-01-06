package com.example.humblrvsv.dto.link


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkListingDataDto(
    val after: String,
    val children: List<LinkChildrenDto>,
    val before: String?
)