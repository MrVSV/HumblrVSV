package com.example.humblrvsv.data.api.dto.linkdto

import com.example.humblrvsv.data.api.dto.Media
import com.example.humblrvsv.data.api.dto.ThingDto
import com.example.humblrvsv.domain.model.Post
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostDto(
    override val kind: String,
    val data: PostDataDto,
):ThingDto {
    @JsonClass(generateAdapter = true)
    data class PostDataDto(
        val subreddit: String,
        @Json(name = "selftext")
        val selfText: String?,
        @Json(name = "author_fullname")
        val authorFullname: String,
        val saved: Boolean,
        val title: String,
        @Json(name = "subreddit_name_prefixed")
        val subredditNamePrefixed: String,
        val name: String,
        val score: Int,
        val thumbnail: String?,
        @Json(name = "post_hint")
        val postHint: String?,
        val created: Double,
        @Json(name = "url_overridden_by_dest")
        val urlOverriddenByDest: String?,
        @Json(name = "subreddit_id")
        val subredditId: String,
        val id: String,
        val author: String,
        @Json(name = "num_comments")
        val numComments: Int,
        val permalink: String,
        val url: String,
        val media: Media?,
        @Json(name = "is_video")
        val isVideo: Boolean,
        val likes: Boolean?,
    )

    fun toPost(): Post {
        val dir = when (data.likes) {
            null -> 0
            true -> 1
            false -> -1
        }
        return Post(
            selfText = data.selfText,
            authorFullname = data.authorFullname,
            saved = data.saved,
            title = data.title,
            subredditNamePrefixed = data.subredditNamePrefixed,
            name = data.name,
            score = data.score,
            //        thumbnail = data.thumbnail,
            postHint = data.postHint,
            created = data.created,
            //        urlOverriddenByDest = data.urlOverriddenByDest,
            //        subredditId = data.subredditId,
            id = data.id,
            author = data.author,
            numComments = data.numComments,
            permalink = data.permalink,
            url = data.url,
            fallbackUrl = data.media?.redditVideo?.fallbackUrl,
            isVideo = data.isVideo,
            likedByUser = data.likes,
            dir = dir
        )
    }


}