package com.example.humblrvsv.data.api.dto.commentdto


import com.example.humblrvsv.domain.model.CommentListing
import com.example.humblrvsv.domain.tools.toListComment
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentListingDto(
    val kind: String,
    val data: CommentListingDataDto
){
    @JsonClass(generateAdapter = true)
    data class CommentListingDataDto(
        val after: String?,
        val children: List<CommentDto>,
        val before: String?
    )

    fun toCommentListing() = CommentListing(
        children = data.children.toListComment()
    )
}