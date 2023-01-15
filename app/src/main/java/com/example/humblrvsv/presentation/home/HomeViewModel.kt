package com.example.humblrvsv.presentation.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.humblrvsv.domain.model.Post
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.usecase.GetThingListUseCase
import com.example.humblrvsv.domain.usecase.VoteLinkUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import com.example.humblrvsv.tools.Listing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getThingListUseCase: GetThingListUseCase,
    private val voteLinkUseCase: VoteLinkUseCase
) : BaseViewModel() {

    private var job: Job? = null
    private val _listingFlow = MutableStateFlow(Listing.POST)
    private val _sourceFlow = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    var getSubList: Flow<PagingData<Thing>> =
        _listingFlow.asStateFlow().flatMapLatest { listing ->
            _sourceFlow.asStateFlow().flatMapLatest { source ->
                getThingListUseCase.getThingList(listing, source).flow
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


    fun onClick(item: Thing, vote: Int) {
        item as Post
        getSubList.onEach { pagingdata ->

            }.cachedIn(viewModelScope)
        }

//            var isLiked = item.likedByUser
//            var dir = 0
//            if (vote == ClickableView.UP_VOTE.vote) when (isLiked) {
//                true -> {
//                    dir = 0
//                    isLiked = null
//                }
//                false -> {
//                    dir = 1
//                    isLiked = true
//                }
//                null -> {
//                    dir = 1
//                    isLiked = true
//                }
//            }
//            else if (vote == ClickableView.DOWN_VOTE.vote) when (isLiked) {
//                true -> {
//                    dir = -1
//                    isLiked = false
//                }
//                false -> {
//                    dir = 0
//                    isLiked = null
//                }
//                null -> {
//                    dir = -1
//                    isLiked = false
//                }
//            }
//            Log.d("vote", "onClick: $isLiked")
//            voteLinkUseCase.voteLink(dir, item.name)
//        }
//    }
}