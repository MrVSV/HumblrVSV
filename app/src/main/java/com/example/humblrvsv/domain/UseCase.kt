package com.example.humblrvsv.domain

import android.content.ContentValues
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.humblrvsv.data.repository.SubredditPagingSource
import com.example.humblrvsv.domain.model.Subreddit
import javax.inject.Inject

class UseCase @Inject constructor(private val pagingSource: SubredditPagingSource) {

    fun getSubList(): Pager<String, Subreddit> {
        Log.d(ContentValues.TAG, "getDigest: ")
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {pagingSource}
        )
    }
}