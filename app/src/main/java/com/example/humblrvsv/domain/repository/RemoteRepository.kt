package com.example.humblrvsv.domain.repository

import com.example.humblrvsv.tools.Listing
import com.example.humblrvsv.domain.model.Thing

interface RemoteRepository {

    suspend fun getThingList(listing: Listing, source: String?, page: String): List<Thing>
}