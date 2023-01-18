package com.example.humblrvsv.domain.usecase

import android.util.Log
import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class VotePostUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend fun votePost(dir: Int, postName: String) {
        Log.d("vote", "voteLink: $dir")
        remoteRepository.votePost(dir, postName)
    }
}