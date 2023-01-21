package com.example.humblrvsv.data.api.dto.other


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    @Json(name = "reddit_video")
    val redditVideo: RedditVideo?
){
    @JsonClass(generateAdapter = true)
    data class RedditVideo(
        @Json(name = "bitrate_kbps")
        val bitrateKbps: Int,
        @Json(name = "fallback_url")
        val fallbackUrl: String,
        val height: Int,
        val width: Int,
        @Json(name = "scrubber_media_url")
        val scrubberMediaUrl: String,
        @Json(name = "dash_url")
        val dashUrl: String,
        val duration: Int,
        @Json(name = "hls_url")
        val hlsUrl: String,
        @Json(name = "is_gif")
        val isGif: Boolean,
        @Json(name = "transcoding_status")
        val transcodingStatus: String
    )
}