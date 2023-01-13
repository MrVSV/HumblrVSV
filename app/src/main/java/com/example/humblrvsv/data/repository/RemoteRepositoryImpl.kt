package com.example.humblrvsv.data.repository

import com.example.humblrvsv.data.api.ApiSubreddit
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.repository.RemoteRepository
import com.example.humblrvsv.tools.toListSubreddit
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiSubreddit: ApiSubreddit
) : RemoteRepository {

    override suspend fun getSubredditList(source: String?, page: String): List<Subreddit> =
        apiSubreddit.getKind(source, page).data.children.toListSubreddit()
}