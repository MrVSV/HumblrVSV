package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.linkdto.PostListingDto


import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPost {

    @GET("/r/popular/{source}")
    suspend fun getPostListing(
        @Path("source") source: String?,
        @Query("after") page: String
    ): PostListingDto

    @POST("/api/vote")
    suspend fun voteLink(
        @Query("dir") dir: Int,
        @Query("id") linkName: String
    )
}