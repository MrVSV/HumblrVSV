package com.example.humblrvsv.data.repository

import com.example.humblrvsv.data.api.ApiLink
import com.example.humblrvsv.data.api.ApiSubreddit
import com.example.humblrvsv.tools.Listing
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.repository.RemoteRepository
import com.example.humblrvsv.tools.toListLink
import com.example.humblrvsv.tools.toListSubreddit
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiSubreddit: ApiSubreddit,
    private val apiLink: ApiLink
) : RemoteRepository {


    override suspend fun getThingList(listing: Listing, source: String?, page: String): List<Thing>{
        return when(listing){
            Listing.SUBREDDIT -> apiSubreddit.getSubredditListing(source, page).data.children.toListSubreddit()
            Listing.LINK -> apiLink.getLinkListing(source, page).data.children.toListLink()
        }
    }

//    override suspend fun getThingList(source: String?, page: String): List<Subreddit> =
//        apiSubreddit.getSubredditListing(source, page).data.children.toListSubreddit()
//
//    suspend fun getLinkList(source: String?, page: String): List<Link> =
//        apiLink.getLinkListing(source, page).data.children.toListLink()
}