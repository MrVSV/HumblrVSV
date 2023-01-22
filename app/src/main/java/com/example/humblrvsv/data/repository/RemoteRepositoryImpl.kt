package com.example.humblrvsv.data.repository

import android.util.Log
import com.example.humblrvsv.data.api.*
import com.example.humblrvsv.data.api.dto.commentdto.CommentDto
import com.example.humblrvsv.data.api.dto.postdto.PostDto
import com.example.humblrvsv.domain.model.*
import com.example.humblrvsv.domain.repository.RemoteRepository
import com.example.humblrvsv.domain.tools.Listing
import com.example.humblrvsv.domain.tools.toListFriend
import com.example.humblrvsv.domain.tools.toListPost
import com.example.humblrvsv.domain.tools.toListSubreddit
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiSubreddit: ApiSubreddit,
    private val apiPost: ApiPost,
    private val apiProfile: ApiProfile,
    private val apiSinglePost: ApiSinglePost,
    private val apiUser: ApiUser
) : RemoteRepository {


    override suspend fun getThingList(
        listing: Listing,
        source: String?,
        page: String
    ): List<Thing> {
        return when (listing) {
            Listing.SUBREDDIT -> apiSubreddit.getSubredditListing(
                source,
                page
            ).data.children.toListSubreddit()
            Listing.POST -> apiPost.getPostListing(source, page).data.children.toListPost()
            Listing.SUBREDDIT_POST -> apiSubreddit.getSubredditPosts(
                source,
                page
            ).data.children.toListPost()
            Listing.SUBSCRIBED_SUBREDDIT -> apiSubreddit.getSubscribed(page).data.children.toListSubreddit()
            Listing.SAVED_POST -> apiPost.getSaved(source, page).data.children.toListPost()
        }
    }

    override suspend fun votePost(dir: Int, postName: String) = apiPost.votePost(dir, postName)

    override suspend fun getSubredditInfo(subredditName: String): Subreddit {
        return apiSubreddit.getSubredditInfo(subredditName).toSubreddit()
    }

    override suspend fun getProfileInfo(): Profile = apiProfile.getProfile().toProfile()

    override suspend fun getFriendList(): List<Friend> =
        apiProfile.getFriends().data.children.toListFriend()

    override suspend fun getSinglePost(url: String): List<Thing> {
        val list = mutableListOf<Thing>()
        apiSinglePost.getSinglePost(url).forEach { responseItem ->
            responseItem.data.children.forEach { child ->
                if (child is PostDto) list.add(child.toPost())
                else if (child is CommentDto) list.add(child.toComment())
            }
        }
        Log.d("моделька", "${list.toList()}")
        return list.toList()
    }

    override suspend fun getUserContent(userName: String): List<Thing> {
        val list = mutableListOf<Thing>()
        apiUser.getUserContent(userName).data.children.forEach { child ->
            if (child is PostDto) list.add(child.toPost())
            else if (child is CommentDto) list.add(child.toComment())
        }
        return list.toList()
    }

    override suspend fun getUserInfo(userName: String): User =
        apiUser.getUserInfo(userName).toUser()
}