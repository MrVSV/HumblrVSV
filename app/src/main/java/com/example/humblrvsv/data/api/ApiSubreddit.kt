package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.postdto.PostListingDto
import com.example.humblrvsv.data.api.dto.subredditdto.SubredditDto
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

    @GET("/{source}")
    suspend fun getSubredditPosts(
        @Path("source") source: String?,
        @Query("after") page: String
    ): PostListingDto

    @GET("/{source}/about")
    suspend fun getSubredditInfo(
        @Path("source") source: String?
    ): SubredditDto

    @GET("/subreddits/mine/subscriber")
    suspend fun getSubscribed(
        @Query("after") page: String?
    ): SubredditListingDto
}