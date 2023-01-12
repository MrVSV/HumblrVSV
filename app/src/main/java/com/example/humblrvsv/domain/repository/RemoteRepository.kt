package com.example.humblrvsv.domain.repository

import com.example.humblrvsv.data.api.dto.KindDto
import com.example.humblrvsv.domain.model.Subreddit

interface RemoteRepository {

    suspend fun getSubredditList(page: String): List<Subreddit>
}