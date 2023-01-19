package com.example.humblrvsv.presentation.auth

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.humblrvsv.data.api.ApiProfile
import com.example.humblrvsv.data.api.ApiToken
import com.example.humblrvsv.data.api.dto.profiledto.ProfileDto
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

//    private val _userName = MutableSharedFlow<Profile>()
//    val userName = _userName.asSharedFlow()

    private var accessToken = PLUG

    fun createToken(code: String) {
        if (code != PLUG && accessToken != START_REQUEST)
            viewModelScope.launch(Dispatchers.IO) {
                _loadState.emit(LoadState.LOADING)
                accessToken = START_REQUEST
                accessToken = try {
                    apiToken.getToken(code = code).accessToken
                } catch (t: Exception) {
                    _loadState.emit(LoadState.ERROR.apply { message = t.message.toString() })
                    PLUG
                }
                _token.emit(accessToken)
                _loadState.emit(LoadState.SUCCESS)
            }
    }

    companion object {
        const val PLUG = ""
        const val START_REQUEST = "start_request"
    }

    suspend fun getProfileInfo(): Profile = getProfileInfoUseCase.getProfileInfo()

}