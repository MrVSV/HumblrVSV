package com.example.humblrvsv.data.api.dto.commentdto


import com.example.humblrvsv.domain.model.Comment
import com.example.humblrvsv.tools.toListCommentListing
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentDto(
    val kind: String,
    val data: CommentDataDto
) {
    @JsonClass(generateAdapter = true)
    data class CommentDataDto(
        @Json(name = "subreddit_id")
        val subredditId: String?,
        val likes: Boolean?,
        val replies: List<CommentListingDto>,
        val saved: Boolean?,
        val id: String,
        val author: String?,
        @Json(name = "parent_id")
        val parentId: String,
        val score: Int?,
        @Json(name = "author_fullname")
        val authorFullname: String?,
        val body: String?,
        val edited: Boolean?,
        val name: String,
        val permalink: String?,
        val created: Double?,
        @Json(name = "link_id")
        val linkId: String?,
        @Json(name = "subreddit_name_prefixed")
        val subredditNamePrefixed: String?,
        val depth: Int,
        val count: Int?,
        val children: List<String>?
    )

    fun toComment() = Comment(
        id = data.id,
        name = data.name,
        likedByUser = data.likes,
        replies = data.replies.toListCommentListing(),
        author = data.author,
        score = data.score,
        body = data.body,
        permalink = data.permalink,
        created = data.created,
        linkId = data.linkId,
        subredditNamePrefixed = data.subredditNamePrefixed
    )
}