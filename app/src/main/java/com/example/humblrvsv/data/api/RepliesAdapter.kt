package com.example.humblrvsv.data.api

import android.util.Log
import com.example.humblrvsv.data.api.dto.SinglePostListingDto
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

class RepliesAdapter {
    @ToJson
    fun toJson(response: SinglePostListingDto): String? {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(SinglePostListingDto::class.java)
        return adapter.toJson(response)
    }
    @FromJson
    fun fromJson(json: List<SinglePostListingDto>): SinglePostListingDto? {
        val newJson = json.toString().replace("replies': \"\"","\"replies\": null")
    Log.d("адаптер", "$json")
    Log.d("адаптер", newJson)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(SinglePostListingDto::class.java)
        return adapter.fromJson(newJson)
    }
}