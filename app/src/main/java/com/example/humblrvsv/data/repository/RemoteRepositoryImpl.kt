package com.example.humblrvsv.data.repository

import com.example.humblrvsv.data.api.ApiMain
import com.example.humblrvsv.data.api.dto.KindDto
import com.example.humblrvsv.data.api.dto.ListingDto
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.repository.RemoteRepository
import com.example.humblrvsv.tools.toListSubreddit
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiMain: ApiMain
) : RemoteRepository {

    override suspend fun getSubredditList(page: String): List<Subreddit> =
        apiMain.getKind(page).data.children.toListSubreddit()
}