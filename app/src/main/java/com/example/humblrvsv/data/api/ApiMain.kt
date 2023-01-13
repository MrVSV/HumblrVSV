package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.KindDto
import com.example.humblrvsv.data.api.dto.rawsub.SubredditListingDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMain {

    @GET("/subreddits/{source}")
    suspend fun getKind(
        @Path("source") source: String?,
        @Query("after") page: String
    ): SubredditListingDto

}