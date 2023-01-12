package com.example.humblrvsv.data.api.dto

import com.example.humblrvsv.data.api.dto.rawsub.SubredditListingDto
import com.example.humblrvsv.domain.model.Subreddit
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SubredditDto(
    val kind: String,
    val data: SubredditDataDto,
) {

    @JsonClass(generateAdapter = true)
    class SubredditDataDto(
        @Json(name = "display_name")
        val displayName: String?,
        @Json(name = "header_img")
        val headerImg: String?,
        val title: String?,
        @Json(name = "icon_img")
        val iconImg: String?,
        @Json(name = "display_name_prefixed")
        val displayNamePrefixed: String?,
        val subscribers: Int?,
        val name: String,
        @Json(name = "community_icon")
        val communityIcon: String?,
        @Json(name = "banner_background_image")
        val bannerBackgroundImage: String?,
        @Json(name = "description_html")
        val descriptionHtml: String?,
        val created: Double?,
        @Json(name = "user_is_subscriber")
        val userIsSubscriber: Boolean?,
        @Json(name = "public_description_html")
        val publicDescriptionHtml: String?,
        @Json(name = "banner_img")
        val bannerImg: String?,
        @Json(name = "banner_background_color")
        val bannerBackgroundColor: String?,
        val id: String?,
        val over18: Boolean?,
        @Json(name = "header_title")
        val description: String?,
        val url: String?,
        @Json(name = "mobile_banner_image")
        val mobileBannerImage: String?,
    )
    fun toSubreddit() = Subreddit(
        namePrefixed = data.displayNamePrefixed,
        url = data.url,
        isUserSubscriber = data.userIsSubscriber,
        description = data.description,
        subscribers = data.subscribers,
        created = data.created,
        id = data.id,
        name = data.name
    )
}
