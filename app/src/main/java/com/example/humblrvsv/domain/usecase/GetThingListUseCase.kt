package com.example.humblrvsv.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.humblrvsv.tools.Listing
import com.example.humblrvsv.data.repository.PagingSource
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.repository.RemoteRepository
import javax.inject.Inject

class GetThingListUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    fun getThingList(listing: Listing, source: String?): Pager<String, Thing> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {PagingSource(remoteRepository, source, listing)}
    )
}