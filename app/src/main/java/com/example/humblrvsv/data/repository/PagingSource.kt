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
        Log.i(TAG, "source: $source")
//        return kotlin.runCatching {
//
//        }.fold(
//            onSuccess = {
//                Log.i(TAG, "что-то грузит")
        return LoadResult.Page(
            data = repository.getThingList(listing, source, page),
            prevKey = null,
            nextKey = if (repository.getThingList(listing, source, page).isEmpty()) null else repository.getThingList(listing, source, page).last().name
        )
//
//            }, onFailure = {
//                Log.i(TAG, "не грузит нихрена")
//                LoadResult.Error(it)
//            }
//        )
    }

    private companion object {
        private const val FIRST_PAGE = ""
    }
}


