package com.example.humblrvsv.data.api.dto.commentdto


import com.example.humblrvsv.data.api.dto.ThingDto
import com.example.humblrvsv.domain.model.Comment
import com.example.humblrvsv.domain.model.CommentListing
import com.example.humblrvsv.domain.tools.toListComment
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentListingDto(
    val kind: String,
    val data: CommentListingDataDto
) {
    @JsonClass(generateAdapter = true)
    data class CommentListingDataDto(
        val after: String?,
        val children: List<ThingDto>,
        val before: String?
    )

    fun toCommentListing(): CommentListing {
        val children = mutableListOf<Comment>()
        data.children.forEach {
            if (it is CommentDto) children.add(it.toComment())
        }
        return CommentListing(children = children)
    }
}