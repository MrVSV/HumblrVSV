package com.example.humblrvsv.data.api.dto.linkdto


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostListingDto(
    val kind: String,
    val data: PostListingDataDto
){
    @JsonClass(generateAdapter = true)
    data class PostListingDataDto(
        val after: String,
        val children: List<PostDto>,
        val before: Any?
    )
}