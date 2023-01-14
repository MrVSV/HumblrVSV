package com.example.humblrvsv.data.api.dto.linkdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkListingDto(
    val kind: String,
    val data: LinkListingDataDto
){
    @JsonClass(generateAdapter = true)
    data class LinkListingDataDto(
        val after: String,
        val children: List<LinkDto>,
        val before: Any?
    )
}