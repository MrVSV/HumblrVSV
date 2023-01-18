package com.example.humblrvsv.presentation.favorites

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.humblrvsv.domain.model.Thing
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

    private var job: Job? = null
    private val _favoritesFlow = MutableStateFlow(Listing.SAVED_POST)


    /**можно сделать по-другому**/
    fun setModel(listing: Listing, refresh: () -> Unit) {
        job?.cancel()
        job = viewModelScope.launch {
            _favoritesFlow.value = listing
            refresh()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    var favoritesList: Flow<PagingData<Thing>> =
        _favoritesFlow.asStateFlow().flatMapLatest { listing ->
                getFavoritesUseCase.getFavorites(listing, "").flow
        }.cachedIn(CoroutineScope(Dispatchers.IO))

    /**когда-нибудь тут будут работающие лайки**/

}