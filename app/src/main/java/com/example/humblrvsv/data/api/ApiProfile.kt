package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.profiledto.ProfileDto
import retrofit2.http.GET

interface ApiProfile {

    @GET("/api/v1/me")
    suspend fun getProfile(
   ): ProfileDto
}