package com.example.humblrvsv.domain.usecase

import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class GetUserContentUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend fun getUserContent(userName: String): List<Thing> =
        remoteRepository.getSinglePost("user", userName)
}