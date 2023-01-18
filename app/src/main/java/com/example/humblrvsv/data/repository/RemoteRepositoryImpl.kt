package com.example.humblrvsv.data.repository

import com.example.humblrvsv.data.api.ApiPost
import com.example.humblrvsv.data.api.ApiSubreddit
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.tools.Listing
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.repository.RemoteRepository
import com.example.humblrvsv.domain.tools.toListPost
import com.example.humblrvsv.domain.tools.toListSubreddit
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiSubreddit: ApiSubreddit,
    private val apiPost: ApiPost
) : RemoteRepository {


    override suspend fun getThingList(listing: Listing, source: String?, page: String): List<Thing>{
        return when(listing){
            Listing.SUBREDDIT -> apiSubreddit.getSubredditListing(source, page).data.children.toListSubreddit()
            Listing.POST -> apiPost.getPostListing(source, page).data.children.toListPost()
            Listing.SUBREDDIT_POST -> apiSubreddit.getSubredditPosts(source, page).data.children.toListPost()
        }
    }

    override suspend fun votePost(dir: Int, postName: String) = apiPost.votePost(dir, postName)

    override suspend fun getSubredditInfo(subredditName: String): Subreddit {
       return apiSubreddit.getSubredditInfo(subredditName).toSubreddit()
    }

//    override suspend fun getThingList(source: String?, page: String): List<Subreddit> =
//        apiSubreddit.getSubredditListing(source, page).data.children.toListSubreddit()
//
//    suspend fun getLinkList(source: String?, page: String): List<Post> =
//        apiPost.getLinkListing(source, page).data.children.toListLink()
}