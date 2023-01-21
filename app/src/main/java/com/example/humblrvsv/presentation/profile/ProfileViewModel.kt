package com.example.humblrvsv.presentation.profile


import androidx.lifecycle.viewModelScope
import com.example.humblrvsv.domain.model.Profile
import com.example.humblrvsv.domain.repository.RemoteRepository
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.domain.usecase.GetProfileInfoUseCase
import com.example.humblrvsv.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileInfoUseCase: GetProfileInfoUseCase,
    private val repo: RemoteRepository
) : BaseViewModel() {

    private val _userInfoFlow = MutableSharedFlow<Profile>()
    val userFlow = _userInfoFlow.asSharedFlow()

    fun getUserInfo(){
        viewModelScope.launch(Dispatchers.IO + handler){
            _loadState.value = LoadState.LOADING_STAGE_1
            _userInfoFlow.emit(getProfileInfoUseCase.getProfileInfo())
            _loadState.value = LoadState.SUCCESS
        }
    }

//    fun onClick(){
//        viewModelScope.launch(Dispatchers.IO) {
//            repo.getSinglePost()
//        }
//    }

}