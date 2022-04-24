package com.challenge.mozper.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.mozper.base.DataResource
import com.challenge.mozper.domain.use_cases.CheckUserCredentialUseCase
import com.challenge.mozper.domain.use_cases.SaveUserCredentialUseCase
import com.challenge.mozper.presentation.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkUserCredentialUseCase: CheckUserCredentialUseCase,
    private val saveUserCredentialUseCase: SaveUserCredentialUseCase
) : ViewModel() {

    private val _mutableUserState = MutableLiveData<UserState>(UserState())
    val userState: LiveData<UserState> = _mutableUserState

    init {
        existUserSessionAvailable()
    }

    fun saveUser(email: String) {
        saveUserCredentialUseCase(email)
        existUserSessionAvailable()
    }

    private fun existUserSessionAvailable() {
        checkUserCredentialUseCase().onEach { result ->
            when(result) {
                is DataResource.Error -> result.message?.let { _mutableUserState.postValue(UserState(error = it)) }
                is DataResource.Loading -> { _mutableUserState.postValue(UserState(isLoading = true)) }
                is DataResource.Success -> _mutableUserState.postValue(UserState(user = result.data))
            }
        }.launchIn(viewModelScope)
    }

}