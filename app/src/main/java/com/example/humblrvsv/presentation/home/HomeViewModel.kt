package com.example.humblrvsv.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.usecase.GetThingListUseCase
import com.example.humblrvsv.domain.usecase.VotePostUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import com.example.humblrvsv.domain.tools.Listing
import com.example.humblrvsv.domain.tools.LocalChange
import com.example.humblrvsv.domain.tools.OnChange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getThingListUseCase: GetThingListUseCase,
) : BaseViewModel() {

    private var job: Job? = null
    private val _listingFlow = MutableStateFlow(Listing.POST)
    private val _sourceFlow = MutableStateFlow("")


    /**можно сделать по-другому**/
    fun setModel(listing: Listing, refresh: () -> Unit) {
        job?.cancel()
        job = viewModelScope.launch {
            _listingFlow.value = listing
            refresh()
        }
    }

    fun setSource(source: String, refresh: () -> Unit) {
        job?.cancel()
        job = viewModelScope.launch {
            _sourceFlow.value = source
            refresh()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    var thingList: Flow<PagingData<Thing>> =
        _listingFlow.asStateFlow().flatMapLatest { listing ->
            _sourceFlow.asStateFlow().flatMapLatest { source ->
                getThingListUseCase.getThingList(listing, source).flow
            }.cachedIn(CoroutineScope(Dispatchers.IO))
        }.cachedIn(CoroutineScope(Dispatchers.IO))

            /**когда-нибудь тут будут работающие лайки**/

}