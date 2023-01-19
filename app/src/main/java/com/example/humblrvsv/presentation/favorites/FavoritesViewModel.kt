package com.example.humblrvsv.presentation.favorites

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.Change
import com.example.humblrvsv.domain.tools.FavoritesQuery
import com.example.humblrvsv.domain.tools.Listing
import com.example.humblrvsv.domain.usecase.GetFavoritesUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel@Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
) : BaseViewModel() {

    private val queryFavorites = FavoritesQuery()
    private val _favoritesFlow = MutableStateFlow(Change(queryFavorites))

    fun setModel(position: Int) {
        queryFavorites.listing = if (position == 0) Listing.SAVED_POST else Listing.SUBSCRIBED_SUBREDDIT
        _favoritesFlow.value = Change(queryFavorites)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun favoritesList(source: String?): Flow<PagingData<Thing>> =
        _favoritesFlow.asStateFlow().flatMapLatest { query ->
                getFavoritesUseCase.getFavorites(query.value.listing, source).flow
        }.cachedIn(CoroutineScope(Dispatchers.IO))

}