package com.example.humblrvsv.domain.usecase

import android.util.Log
import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class VoteLinkUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend fun voteLink(dir: Int, linkName: String) {
        Log.d("vote", "voteLink: $dir")
        remoteRepository.voteLink(dir, linkName)
    }
}