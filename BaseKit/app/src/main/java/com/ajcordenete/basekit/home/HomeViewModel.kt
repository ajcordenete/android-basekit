package com.ajcordenete.basekit.home

import android.os.Bundle
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.core.ext.launch
import com.ajcordenete.data.feature.user.UserRepository
import com.ajcordenete.domain.error
import com.ajcordenete.domain.get
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    private val _uiState = MutableSharedFlow<HomeUiState>()
    val uiState = _uiState.asSharedFlow()

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

    fun getUsers() = launch(
        action = {
            val result = userRepository.updateUsersFromRemote()
            if(result.isSuccess) {
                _uiState.emit(
                    HomeUiState.ShowUsers(result.get())
                )
            } else if(result.isFailure) {
                _uiState.emit(
                    HomeUiState.ShowError(result.error().message.orEmpty())
                )
            }
        },
        onError = {
            _uiState.emit(
                HomeUiState.ShowError(it.message.orEmpty())
            )
        }
    )
}