package com.example.humblrvsv.data.api.dto.rawsub


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
        val dist: Int,
        val modhash: Any?,
        @Json(name = "geo_filter")
        val geoFilter: Any?,
        val children: List<LinkDto>,
        val before: Any?
    )
}