package com.example.humblrvsv.data.api.dto

import com.example.humblrvsv.domain.model.Listing
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingDto(
    val after: String?,
    val dist: Int?,
    val children: List<KindDto>?,
    val before: String?
){

    fun toListing() = Listing( after = after, before = before)

}