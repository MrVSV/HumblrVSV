package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.SinglePostListingDto
import retrofit2.http.GET


interface ApiSinglePost{

    @GET("/r/shitposting/comments/10gym2w/novus/")
    suspend fun getSinglePost(
//        @Path("user_name") userName: String?,
//        @Query("after") page: String,
//        @Query("type") type: String = "links"
    ): List<SinglePostListingDto>
}