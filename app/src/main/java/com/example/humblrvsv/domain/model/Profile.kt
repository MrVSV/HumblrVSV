package com.example.humblrvsv.domain.model

import com.example.humblrvsv.data.api.dto.profiledto.ProfileDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Profile(
    val avatarFull: String,
    val friendsCount: Int,
    val id: String,
//    val awarderKarma: Int,
//    val avatarSmall: String,
//    val awardeeKarma: Int,
//    val linkKarma: Int,
    val totalKarma: Int,
    val name: String,
//    val prefClickgadget: Int,
    val created: Double,
//    val commentKarma: Int,
    val title: String,
    val subscribers: Int,
    val displayNamePrefixed: String,
    val url: String,
//    val subredditType: String
)
