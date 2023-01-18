package com.example.humblrvsv.domain.model

data class Subreddit(
    val namePrefixed: String,
    val url: String?,
    val isUserSubscriber: Boolean?,
    val description: String?,
    val subscribers: Int?,
    val created: Double?,
    override val id: String,
    override val name: String
): Thing