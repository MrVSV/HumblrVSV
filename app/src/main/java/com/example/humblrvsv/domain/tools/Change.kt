package com.example.humblrvsv.domain.tools

class Change<T>(val value: T)

data class Query(
    var listing: Listing = Listing.POST,
    var source: String = ""
)

data class FavoritesQuery(
    var listing: Listing = Listing.SAVED_POST,
    var source: String = ""
)