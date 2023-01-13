package com.example.humblrvsv.domain.model

data class Link(
    val selfText: String?,
    val authorFullname: String,
    val saved: Boolean,
    val title: String,
    val subredditNamePrefixed: String,
    val name: String,
    val score: Int,
    val thumbnail: String,
    val postHint: String?,
    val created: Double,
    val urlOverriddenByDest: String,
    val subredditId: String,
    val id: String,
    val author: String,
    val numComments: Int,
    val permalink: String,
    val url: String,
    val fallbackUrl: String?,
    val isVideo: Boolean,
)