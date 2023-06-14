package com.ajcordenete.basekit.user

import android.os.Bundle
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.core.ext.launch
import com.ajcordenete.data.feature.user.UserRepository
import com.ajcordenete.domain.error
import com.ajcordenete.domain.get
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    private val _uiState = MutableSharedFlow<UserUiState>()
    val uiState = _uiState.asSharedFlow()

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

    fun getUsers() = launch(
        action = {
            val result = userRepository.updateUsersFromRemote()
            if(result.isSuccess) {
                _uiState.emit(
                    UserUiState.ShowUsers(result.get())
                )
            } else if(result.isFailure) {
                _uiState.emit(
                    UserUiState.ShowError(result.error().message.orEmpty())
                )
            }
        },
        onError = {
            _uiState.emit(
                UserUiState.ShowError(it.message.orEmpty())
            )
        }
    )
}