package com.example.humblrvsv.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class SubredditPagingSource @Inject constructor(
    private val repository: RemoteRepository,
    private val source: String?
) : PagingSource<String, Subreddit>() {

    override fun getRefreshKey(state: PagingState<String, Subreddit>): String = FIRST_PAGE

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Subreddit> {
        Log.d(TAG, "load: ")
        val page = params.key ?: FIRST_PAGE
//        Log.i(TAG, "source: $source")
        return kotlin.runCatching {
            repository.getSubredditList(source, page)
        }.fold(
            onSuccess = {
                Log.i(TAG, "что-то грузит")
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else it.last().name
                )

            }, onFailure = {
                Log.i(TAG, "не грузит нихрена")
                LoadResult.Error(it)
            }
        )
    }

    private companion object {
        private const val FIRST_PAGE = ""
    }
}
