package com.example.humblrvsv.domain.repository

import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.tools.Listing
import com.example.humblrvsv.domain.model.Thing


interface RemoteRepository {

    suspend fun getThingList(listing: Listing, source: String?, page: String): List<Thing>

    suspend fun votePost(dir: Int, postName: String)

    suspend fun getSubredditInfo(subredditName: String): Subreddit
}