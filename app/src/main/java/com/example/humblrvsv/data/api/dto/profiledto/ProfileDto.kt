package com.example.humblrvsv.data.api.dto.profiledto


import com.example.humblrvsv.domain.model.Profile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileDto(
    @Json(name = "subreddit")
    val additionalInfo: AdditionalInfo,
    @Json(name = "snoovatar_img")
    val snoovatarImg: String,
    @Json(name = "num_friends")
    val numFriends: Int,
    val id: String,
    @Json(name = "awarder_karma")
    val awarderKarma: Int,
    @Json(name = "icon_img")
    val iconImg: String,
    @Json(name = "awardee_karma")
    val awardeeKarma: Int,
    @Json(name = "link_karma")
    val linkKarma: Int,
    @Json(name = "total_karma")
    val totalKarma: Int,
    val name: String,
    @Json(name = "pref_clickgadget")
    val prefClickgadget: Int,
    val created: Double,
    @Json(name = "comment_karma")
    val commentKarma: Int,
    @Json(name = "has_subscribed")
    val hasSubscribed: Boolean
) {
    @JsonClass(generateAdapter = true)
    data class AdditionalInfo(
        val title: String,
        val subscribers: Int,
        @Json(name = "display_name_prefixed")
        val displayNamePrefixed: String,
        val url: String,
        @Json(name = "subreddit_type")
        val subredditType: String
    )

    fun toProfile() = Profile(
        avatarFull = snoovatarImg,
        friendsCount = numFriends,
        id = id,
        totalKarma = totalKarma,
        name = name,
        created = created,
        title = additionalInfo.title,
        subscribers = additionalInfo.subscribers,
        displayNamePrefixed = additionalInfo.displayNamePrefixed,
        url = additionalInfo.url,
        avatarSmall = iconImg
    )
}