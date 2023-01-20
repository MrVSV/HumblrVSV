package com.example.humblrvsv.presentation.singlesubreddit

import androidx.lifecycle.viewModelScope
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.domain.tools.Listing
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.domain.usecase.GetSubredditFeedUseCase
import com.example.humblrvsv.domain.usecase.GetSubredditInfoUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleSubredditViewModel @Inject constructor(
    private val getSubredditFeedUseCase: GetSubredditFeedUseCase,
    private val getSubredditInfoUseCase: GetSubredditInfoUseCase
) : BaseViewModel() {

    private val _subredditInfo = MutableSharedFlow<Subreddit>()
    val subredditInfo = _subredditInfo.asSharedFlow()

    fun postList(subredditName: String) =
        getSubredditFeedUseCase.getSubredditFeed(Listing.SUBREDDIT_POST, subredditName).flow

    fun getSubredditInfo(subredditName: String){
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loadState.emit(LoadState.LOADING_STAGE_1)
            _subredditInfo.emit(getSubredditInfoUseCase.getSubredditInfo(subredditName))
            _loadState.emit(LoadState.SUCCESS)
        }

    }
}