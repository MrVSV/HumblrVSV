package com.example.humblrvsv.domain

import android.content.ContentValues
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.humblrvsv.tools.Listing
import com.example.humblrvsv.data.repository.SubredditPagingSource
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class UseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    fun getSubList(listing: Listing, source: String?): Pager<String, Thing> {
        Log.d(ContentValues.TAG, "getDigest: ")
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {SubredditPagingSource(remoteRepository, source, listing)}
        )
    }
}