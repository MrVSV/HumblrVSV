package com.example.humblrvsv.presentation.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.Change
import com.example.humblrvsv.domain.tools.Listing
import com.example.humblrvsv.domain.tools.Query
import com.example.humblrvsv.domain.usecase.GetThingListUseCase
import com.example.humblrvsv.domain.usecase.VotePostUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getThingListUseCase: GetThingListUseCase,
    private val votePostUseCase: VotePostUseCase
) : BaseViewModel() {

    private val query = Query()
    private val _thingFlow = MutableStateFlow(Change(query))

    fun setQuery(position: Int, isTabSource: Boolean) =
        if (isTabSource) setSource(position) else setModel(position)

    private fun setModel(position: Int) {
        query.listing = if (position == 0) Listing.POST else Listing.SUBREDDIT
        _thingFlow.value = Change(query)
    }

    private fun setSource(position: Int) {
        query.source = if (position == 0) POPULAR_ else NEW_
        _thingFlow.value = Change(query)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    var thingList: Flow<PagingData<Thing>> =
        _thingFlow.asStateFlow().flatMapLatest { query ->
            getThingListUseCase.getThingList(query.value.listing, query.value.source).flow
        }.cachedIn(CoroutineScope(Dispatchers.IO))

    companion object {
        private const val NEW_ = "new"
        private const val POPULAR_ = ""
    }

    fun voteUp(item: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            when {
                item.likedByUser == true -> {
                    votePostUseCase.votePost(0, item.name)
                    item.likedByUser = null
                }
                item.likedByUser != true -> {
                    votePostUseCase.votePost(1, item.name)
                    item.likedByUser = true
                }
            }
        }
    }

    fun voteDown(item: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            when (item.likedByUser) {
                false -> {
                    votePostUseCase.votePost(0, item.name)
                    item.likedByUser = null
                }
                else -> {
                    votePostUseCase.votePost(-1, item.name)
                    item.likedByUser = false
                }
            }
        }
    }
}