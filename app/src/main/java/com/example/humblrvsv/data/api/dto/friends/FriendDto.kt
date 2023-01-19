package com.example.humblrvsv.data.api.dto.friends

import com.example.humblrvsv.domain.model.Friend
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FriendDto(
    val date: Double,
    @Json(name = "rel_id")
    val relId: String,
    val name: String,
    val id: String
) {
    fun toFriend() = Friend(name = name, id = id)
}
