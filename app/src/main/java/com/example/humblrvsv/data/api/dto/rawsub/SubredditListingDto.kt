package com.example.humblrvsv.data.api.dto.rawsub

import com.example.humblrvsv.data.api.dto.SubredditDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditListingDto(
    val kind: String,
    val data: SubredditListingDataDto,
    ){

    @JsonClass(generateAdapter = true)
    data class SubredditListingDataDto(
        val after: String?,
        val dist: Int?,
        val children: List<SubredditDto>,
        val before: String?
    )
}