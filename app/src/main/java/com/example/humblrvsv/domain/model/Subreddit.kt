package com.example.humblrvsv.domain.model

data class Subreddit(
    val namePrefixed: String?,
    val url: String?,
    val isUserSubscriber: Boolean?,
    val description: String?,
    val subscribers: Int?,
    val created: Double?,
    val id: String?,
    val name: String
)