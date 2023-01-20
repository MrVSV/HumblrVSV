package com.example.humblrvsv.data.api

import retrofit2.http.GET


interface ApiSinglePost{

    @GET("/r/redditdev/comments/10fl9tp/how_to_vary_the_count_on_a_json_url/")
    suspend fun getSinglePost(
//        @Path("user_name") userName: String?,
//        @Query("after") page: String,
//        @Query("type") type: String = "links"
    ): String
}
