package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.KindDto
import com.example.humblrvsv.data.api.dto.rawsub.SubredditListingDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMain {

    @GET("/subreddits/")
    suspend fun getKind(@Query("after") page: String): SubredditListingDto

}