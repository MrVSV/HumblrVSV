package com.example.humblrvsv.domain.usecase

import com.example.humblrvsv.domain.model.User
import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend fun getUserInfo(userName: String): User = remoteRepository.getUserInfo(userName)
}