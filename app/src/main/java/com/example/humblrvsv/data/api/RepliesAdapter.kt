package com.example.humblrvsv.data.api

import com.example.humblrvsv.data.api.dto.SinglePostListingDto
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

class RepliesAdapter() {
    @ToJson
    fun toJson(response: List<SinglePostListingDto>): String? {
        return null
    }
    @FromJson
    fun fromJson(json: String): SinglePostListingDto? {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(SinglePostListingDto::class.java)
        return adapter.fromJson(json)
    }
}