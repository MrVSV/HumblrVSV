package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.KindDto
import retrofit2.http.GET

interface ApiSaved {

    @GET("/user/CollectionOk2095/saved/")
    suspend fun getSaved(
    ): KindDto
}