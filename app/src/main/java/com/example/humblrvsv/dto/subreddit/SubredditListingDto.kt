package com.example.humblrvsv.dto.subreddit


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditListingDto(
    val kind: String,
    @Json(name = "data")
    val listingData: SubredditListingDataDto
)

