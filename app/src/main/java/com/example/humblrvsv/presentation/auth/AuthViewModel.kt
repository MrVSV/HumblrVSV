package com.example.humblrvsv.presentation.auth

import androidx.lifecycle.viewModelScope
import com.example.humblrvsv.data.api.ApiToken
import com.example.humblrvsv.domain.model.Profile
import com.example.humblrvsv.presentation.base.BaseViewModel
import com.example.humblrvsv.domain.tools.LoadState
import com.example.humblrvsv.domain.usecase.GetProfileInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val apiToken: ApiToken,
    private val getProfileInfoUseCase: GetProfileInfoUseCase
) : BaseViewModel() {

    private val _token = MutableSharedFlow<String>()
    val token = _token.asSharedFlow()

    private val _profileInfo = MutableSharedFlow<Profile>()
    val profileInfo = _profileInfo.asSharedFlow()

    private var accessToken = PLUG

    fun createToken(code: String) {
        if (code != PLUG && accessToken != START_REQUEST)
            viewModelScope.launch(Dispatchers.IO) {
                _loadState.emit(LoadState.LOADING_STAGE_1)
                accessToken = START_REQUEST
                accessToken = try {
                    apiToken.getToken(code = code).accessToken
                } catch (t: Exception) {
                    _loadState.emit(LoadState.ERROR.apply { message = t.message.toString() })
                    PLUG
                }
                _token.emit(accessToken)
                _loadState.emit(LoadState.LOADING_STAGE_2)
                _profileInfo.emit(getProfileInfo())
                _loadState.emit(LoadState.SUCCESS)
            }
    }

    companion object {
        const val PLUG = ""
        const val START_REQUEST = "start_request"
    }

    private suspend fun getProfileInfo(): Profile = getProfileInfoUseCase.getProfileInfo()

}