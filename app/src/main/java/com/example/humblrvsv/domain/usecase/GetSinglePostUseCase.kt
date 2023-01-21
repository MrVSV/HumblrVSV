package com.example.humblrvsv.domain.usecase

import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class GetSinglePostUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend fun getSinglePost(url: String) = remoteRepository.getSinglePost("comments", url)
}