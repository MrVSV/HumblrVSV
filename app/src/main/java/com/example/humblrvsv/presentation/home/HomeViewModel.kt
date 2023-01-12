package com.example.humblrvsv.presentation.home

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.humblrvsv.domain.UseCase
import com.example.humblrvsv.domain.model.Subreddit
import com.example.humblrvsv.presentation.BaseViewModel
import com.example.humblrvsv.presentation.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UseCase,
) : BaseViewModel() {

    private val _subreddit = MutableStateFlow<PagingData<Subreddit>>(PagingData.empty<Subreddit>())
    val subreddit = _subreddit.asStateFlow()


    @SuppressLint("CheckResult")
    fun getData() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loadState.value = LoadState.LOADING
            subList()
            _loadState.value = LoadState.SUCCESS
        }
    }

    fun subList() = useCase.getSubList().flow
}