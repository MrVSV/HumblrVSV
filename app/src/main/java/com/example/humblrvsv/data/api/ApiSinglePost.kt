package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.postdto.SinglePostListingDto
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiSinglePost{

    @GET("/comments/{url}/")
    suspend fun getSinglePost(
        @Path("url") url: String
    ): List<SinglePostListingDto>
}
