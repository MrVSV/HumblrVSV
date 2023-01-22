package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.postdto.SinglePostListingDto
import com.example.humblrvsv.data.api.dto.userdto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiUser {

    @GET("/user/{url}/")
    suspend fun getUserContent(
        @Path("url") url: String,
    ): SinglePostListingDto

    @GET("/user/{username}/about/")
    suspend fun getUserInfo(
        @Path("username") userName: String
    ): UserDto

}