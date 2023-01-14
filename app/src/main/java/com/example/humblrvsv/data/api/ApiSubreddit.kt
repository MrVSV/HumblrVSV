package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.subredditdto.SubredditListingDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiSubreddit {

    @GET("/subreddits/{source}")
    suspend fun getSubredditListing(
        @Path("source") source: String?,
        @Query("after") page: String
    ): SubredditListingDto

}