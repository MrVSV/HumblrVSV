package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.friends.FriendListingDto
import com.example.humblrvsv.data.api.dto.profiledto.ProfileDto
import retrofit2.http.GET

interface ApiProfile {

    @GET("/api/v1/me")
    suspend fun getProfile(): ProfileDto

    @GET("/api/v1/me/friends")
    suspend fun getFriends(): FriendListingDto
}