package com.example.humblrvsv.data.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Kind(
    val kind: String,
    val data: Any
){
    init {
        if (kind == "Listing") data is ListingDto
        if (kind == "t3") data is LinkDto
        if (kind == "t1") data is CommentDto
    }


}

