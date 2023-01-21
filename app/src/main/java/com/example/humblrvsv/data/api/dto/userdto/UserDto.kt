package com.example.humblrvsv.data.api.dto.userdto


import com.example.humblrvsv.domain.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val kind: String,
    val data: UserDataDto
) {
    @JsonClass(generateAdapter = true)
    data class UserDataDto(
        @Json(name = "is_friend")
        val isFriend: Boolean,
        val id: String,
        @Json(name = "icon_img")
        val iconImg: String,
        @Json(name = "total_karma")
        val totalKarma: Int,
        val name: String,
        @Json(name = "snoovatar_img")
        val snoovatarImg: String
    )

    fun toUser(): User = User(
        isFriend = data.isFriend,
        id = data.id,
        iconImg = data.iconImg,
        totalKarma = data.totalKarma,
        name = data.name,
        snoovatarImg = data.snoovatarImg
    )
}