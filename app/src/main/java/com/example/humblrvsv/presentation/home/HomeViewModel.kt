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
    private val votePostUseCase: VotePostUseCase
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
//                    .cachedIn(viewModelScope)
//                    .combine(localChangeFlow, this::merge)
//                    .cachedIn(viewModelScope)
            }.cachedIn(CoroutineScope(Dispatchers.IO))
        }.cachedIn(CoroutineScope(Dispatchers.IO))

            /**когда-нибудь тут будут работающие лайки**/
//    private val localChange = LocalChange()
//    private val localChangeFlow = MutableStateFlow(OnChange(localChange))
//
//    private fun merge(
//        thing: PagingData<Thing>,
//        localChange: OnChange<LocalChange>
//    ) = thing.map { item ->
//        item as Post
//        val localVote = localChange.value.isVoted[item.name]
//        val newThing =  item.copy(likedByUser = localVote)
//        newThing as Thing
//    }
//
//    private suspend fun setFlag(newItem: Thing) {
//        newItem as Post
//        val newFlag = newItem.likedByUser
//        localChange.isVoted[newItem.name] = newFlag
//        localChangeFlow.emit(OnChange(localChange))
//    }
//
//    fun upVote(item: Thing) {
//        item as Post
//        viewModelScope.launch {
//            Log.d("голосование", "upVote до: ${item.likedByUser}")
//            if (item.likedByUser == true) votePostUseCase.votePost(0, item.name)
//            else votePostUseCase.votePost(1, item.name)
//            setFlag(item)
//            Log.d("голосование", "upVote после: ${item.likedByUser}")
//        }
//    }
//
//    fun downVote(item: Thing) {
//        item as Post
//        viewModelScope.launch {
//            Log.d("голосование", "downVote до: ${item.likedByUser}")
//            if (item.likedByUser == false) votePostUseCase.votePost(0, item.name)
//            else votePostUseCase.votePost(-1, item.name)
//            setFlag(item)
//            Log.d("голосование", "downVote после: ${item.likedByUser}")
//        }
//    }
}