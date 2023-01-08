package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.Kind
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiSaved {

    @GET("/user/CollectionOk2095/saved/")
    suspend fun getSaved(
    ): Kind
}