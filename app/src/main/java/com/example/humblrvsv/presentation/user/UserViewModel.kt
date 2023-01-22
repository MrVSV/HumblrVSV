package com.example.humblrvsv.presentation.user

import androidx.lifecycle.viewModelScope
import com.example.humblrvsv.domain.model.Thing
import com.example.humblrvsv.domain.model.User
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.domain.usecase.GetUserContentUseCase
import com.example.humblrvsv.domain.usecase.GetUserInfoUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserContentUseCase: GetUserContentUseCase
) : BaseViewModel() {

    private val _userInfo = MutableSharedFlow<User>()
    val userInfo = _userInfo.asSharedFlow()
    private val _content = MutableStateFlow<List<Thing>>(emptyList())
    val content = _content.asStateFlow()

    fun loadUserData(userName: String){
        viewModelScope.launch(Dispatchers.IO) {
            _loadState.value = LoadState.LOADING_STAGE_1
            _userInfo.emit(getUserInfoUseCase.getUserInfo(userName))
            _content.value = getUserContentUseCase.getUserContent(userName)
            _loadState.value = LoadState.SUCCESS
        }
    }


}