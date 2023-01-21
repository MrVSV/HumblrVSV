package com.example.humblrvsv.domain.model

data class User(
    val isFriend: Boolean,
    val id: String,
    val iconImg: String,
    val totalKarma: Int,
    val name: String,
    val snoovatarImg: String
)
