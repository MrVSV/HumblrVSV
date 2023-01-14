package com.example.humblrvsv.presentation.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.humblrvsv.tools.Listing
import com.example.humblrvsv.domain.UseCase
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UseCase,
) : BaseViewModel() {

    private var job: Job? = null
    private val _listingFlow = MutableStateFlow(Listing.LINK)
    private val _sourceFlow = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getSubList(): Flow<PagingData<Thing>> =
        _listingFlow.asStateFlow().flatMapLatest { listing ->
            _sourceFlow.asStateFlow().flatMapLatest { source ->
                useCase.getSubList(listing, source).flow
            }.cachedIn(CoroutineScope(Dispatchers.IO))
        }.cachedIn(CoroutineScope(Dispatchers.IO))

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
}