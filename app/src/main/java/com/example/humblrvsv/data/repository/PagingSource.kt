package com.example.humblrvsv.data.repository

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.repository.RemoteRepository
import com.example.humblrvsv.domain.tools.Listing
import javax.inject.Inject

class PagingSource @Inject constructor(
    private val repository: RemoteRepository,
    private val source: String?,
    private val listing: Listing
) : PagingSource<String, Thing>() {

    override fun getRefreshKey(state: PagingState<String, Thing>): String = FIRST_PAGE

    @SuppressLint("SuspiciousIndentation")
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Thing> {
        Log.d(TAG, "load: ")
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getThingList(listing, source, page)
        }.fold(
            onSuccess = {
                Log.i(TAG, "что-то грузит")
                PagingSource.LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else it.last().name
                )

            }, onFailure = {
                Log.i(TAG, "не грузит нихрена")
                PagingSource.LoadResult.Error(it)
            }
        )
    }

    private companion object {
        private const val FIRST_PAGE = ""
    }
}


