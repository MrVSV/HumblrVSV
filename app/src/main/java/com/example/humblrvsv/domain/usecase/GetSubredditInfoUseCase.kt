package com.example.humblrvsv.domain.usecase

import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class GetSubredditInfoUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend fun getSubredditInfo(subredditName: String): Subreddit{
        return remoteRepository.getSubredditInfo(subredditName)
    }
}