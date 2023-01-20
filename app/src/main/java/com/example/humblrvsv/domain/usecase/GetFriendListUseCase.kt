package com.example.humblrvsv.domain.usecase

import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class GetFriendListUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend fun getFriendList() = remoteRepository.getFriendList()
}