package com.example.humblrvsv.data.api.dto.subredditdto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditListingDto(
    val kind: String,
    val data: SubredditListingDataDto,
    ){

    @JsonClass(generateAdapter = true)
    data class SubredditListingDataDto(
        val after: String?,
        val children: List<SubredditDto>,
        val before: String?
    )
}