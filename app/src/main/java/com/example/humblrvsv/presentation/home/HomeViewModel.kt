package com.example.humblrvsv.presentation.home

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.Listing
import com.example.humblrvsv.domain.usecase.GetThingListUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getThingListUseCase: GetThingListUseCase,
) : BaseViewModel() {

    private val test = Query()
    private val _listingFlow = MutableStateFlow(Change(test))
   fun setQuery(position: Int, isTabSource: Boolean) =
       if (isTabSource) setSource(position) else setModel(position)

    private fun setModel(position: Int) {
        test.listing = if (position == 0) Listing.POST else Listing.SUBREDDIT
        _listingFlow.value = Change(test)
        Log.e("Kart","${_listingFlow.value}")
    }

    private fun setSource(position: Int) {
        test.source = if (position == 0) NEW_ else OLD_
        _listingFlow.value = Change(test)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    var thingList: Flow<PagingData<Thing>> =
        _listingFlow.asStateFlow().flatMapLatest { query ->
            getThingListUseCase.getThingList(query.value.listing, query.value.source).flow
        }.cachedIn(CoroutineScope(Dispatchers.IO))

    companion object {
        private const val NEW_ = "new"
        private const val OLD_ = ""
    }
}

class Change<T>(val value: T)

data class Query(
    var listing: Listing = Listing.POST,
    var source: String = ""
)