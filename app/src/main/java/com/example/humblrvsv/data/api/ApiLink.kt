package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.linkdto.LinkListingDto
import com.example.humblrvsv.data.api.dto.subredditdto.SubredditListingDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiLink {

    @GET("/r/popular/{source}")
    suspend fun getLinkListing(
        @Path("source") source: String?,
        @Query("after") page: String
    ): LinkListingDto
}