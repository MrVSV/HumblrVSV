package com.example.humblrvsv.groupie

data class Comment(
    val body: String,
    val author: String,
    val score: Int,
    val id: String,
    val children: List<Comment>,
)