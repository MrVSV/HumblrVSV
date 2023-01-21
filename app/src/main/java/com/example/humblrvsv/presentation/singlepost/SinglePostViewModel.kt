package com.example.humblrvsv.presentation.singlepost

import androidx.lifecycle.viewModelScope
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.domain.usecase.GetSinglePostUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SinglePostViewModel @Inject constructor(
    private val getSinglePostUseCase: GetSinglePostUseCase
    ) : BaseViewModel() {

    private val _post = MutableStateFlow<List<Thing>>(emptyList())
    val post = _post.asStateFlow()

//    init {
//        loadFriendList()
//    }

    fun loadSinglePost(url: String){
        viewModelScope.launch(Dispatchers.IO) {
            _loadState.value = LoadState.LOADING_STAGE_1
            _post.value = getSinglePostUseCase.getSinglePost(url)
            _loadState.value = LoadState.SUCCESS
        }
    }

}