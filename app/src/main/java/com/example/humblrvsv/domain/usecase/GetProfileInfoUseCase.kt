package com.example.humblrvsv.domain.usecase

import android.content.ContentValues
import android.util.Log
import com.example.humblrvsv.domain.model.Profile
import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class GetProfileInfoUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend fun getProfileInfo(): Profile = remoteRepository.getProfileInfo()
}