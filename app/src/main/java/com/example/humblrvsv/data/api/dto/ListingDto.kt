package com.example.humblrvsv.data.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingDto(
    val after: String?,
    val dist: Int?,
    val children: List<Kind>?,
    val before: String?
)