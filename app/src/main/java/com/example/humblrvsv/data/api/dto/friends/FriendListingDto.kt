package com.example.humblrvsv.data.api.dto.friends


import com.example.humblrvsv.domain.model.Friend
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FriendListingDto(
    val kind: String,
    val data: FriendListDto
) {
    @JsonClass(generateAdapter = true)
    data class FriendListDto(
        val children: List<FriendDto>
    )
}