package com.example.humblrvsv.domain.model

import com.example.humblrvsv.data.api.dto.commentdto.CommentListingDto
import com.squareup.moshi.Json

data class Comment(
    override val id: String,
    override val name: String,
    val likedByUser: Boolean?,
    val replies: List<CommentListing>,
//    val saved: Boolean?,
    val author: String?,
//    val parentId: String,
    val score: Int?,
//    val authorFullname: String?,
    val body: String?,
//    val edited: Boolean?,
    val permalink: String?,
    val created: Double?,
    val linkId: String?,
    val subredditNamePrefixed: String?,
//    val depth: Int,
//    val count: Int?,
//    val children: List<String>?
    ) : Thing
