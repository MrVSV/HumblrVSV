package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.friends.FriendListingDto
import com.example.humblrvsv.data.api.dto.profiledto.ProfileDto
import com.example.humblrvsv.data.api.dto.userdto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiProfile {

    @GET("/api/v1/me")
    suspend fun getProfile(): ProfileDto

    @GET("/api/v1/me/friends")
    suspend fun getFriends(): FriendListingDto

    @GET("/user/{username}")
    suspend fun getUserInfo(
        @Path("username") userName: String
    ): UserDto
}