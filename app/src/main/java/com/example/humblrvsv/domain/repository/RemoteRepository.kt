package com.example.humblrvsv.domain.repository

import com.example.humblrvsv.domain.model.Subreddit

interface RemoteRepository {

    suspend fun getSubredditList(source: String?, page: String): List<Subreddit>
}