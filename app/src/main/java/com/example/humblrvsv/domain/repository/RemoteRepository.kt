package com.example.humblrvsv.domain.repository

import com.example.humblrvsv.domain.model.*
import com.example.humblrvsv.domain.tools.Listing


interface RemoteRepository {

    suspend fun getThingList(listing: Listing, source: String?, page: String): List<Thing>

    suspend fun votePost(dir: Int, postName: String)

    suspend fun getSubredditInfo(subredditName: String): Subreddit

    suspend fun getProfileInfo(): Profile

    suspend fun getFriendList():List<Friend>

    suspend fun getSinglePost(from:String, url: String):List<Thing>

    suspend fun getUserInfo(userName: String): User
}