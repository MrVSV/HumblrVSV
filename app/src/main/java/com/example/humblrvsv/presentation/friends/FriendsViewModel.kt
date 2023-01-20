package com.example.humblrvsv.presentation.friends

import androidx.lifecycle.viewModelScope
import com.example.humblrvsv.domain.model.Friend
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.domain.usecase.GetFriendListUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val getFriendListUseCase: GetFriendListUseCase,
) : BaseViewModel() {

    private val _friends = MutableStateFlow<List<Friend>>(emptyList())
    val friends = _friends.asStateFlow()

    init {
        loadFriendList()
    }

    private fun loadFriendList(){
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loadState.value = LoadState.LOADING_STAGE_1
            _friends.value = getFriendListUseCase.getFriendList()
            _loadState.value = LoadState.SUCCESS
        }
    }
}