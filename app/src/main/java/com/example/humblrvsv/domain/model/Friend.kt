package com.example.humblrvsv.domain.model

data class Friend(
    override val name: String,
    override val id: String,
) : Thing
