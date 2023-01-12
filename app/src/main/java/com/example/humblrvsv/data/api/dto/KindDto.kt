package com.example.humblrvsv.data.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KindDto(
    val kind: String,
    val data: Any,
) {
    init {
        if (kind == "Listing") data is ListingDto
        if (kind == "t5") data is SubredditDto
        if (kind == "t3")  data is LinkDto
        if (kind == "t1") data is CommentDto
    }

    fun toListingDto(){
        data is ListingDto
    }

    fun toSubredditDto(){
        data is SubredditDto
    }

    fun toLinkDto(){
        data is LinkDto
    }

    fun toCommentDto(){
        data is CommentDto
    }
}

