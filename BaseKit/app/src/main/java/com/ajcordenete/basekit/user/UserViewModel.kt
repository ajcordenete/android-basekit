package com.ajcordenete.basekit.user

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.core.ext.launch
import com.ajcordenete.data.feature.user.UserRepository
import com.ajcordenete.domain.core.DispatcherProvider
import com.ajcordenete.domain.error
import com.ajcordenete.domain.get
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
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

    fun getUsersFlow() = viewModelScope.launch {
        userRepository
            .getUsersFlow()
            .onEach {
                _uiState.emit(
                    UserUiState.ShowUsers(it)
                )
            }
            .catch {
                _uiState.emit(
                    UserUiState.ShowError(it.message.orEmpty())
                )
            }
            .singleOrNull()
    }
}