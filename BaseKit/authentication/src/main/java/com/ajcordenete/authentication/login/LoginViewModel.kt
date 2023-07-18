package com.ajcordenete.authentication.login

import android.os.Bundle
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.core.ext.launch
import com.ajcordenete.core.utils.ResourceManager
import com.ajcordenete.data.feature.auth.AuthRepository
import com.ajcordenete.domain.error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val resourceManager: ResourceManager
): BaseViewModel() {

    private val _uiState = MutableSharedFlow<LoginUiState>()
    val uiState = _uiState.asSharedFlow()

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

    fun login(
        email: String,
        password: String
    ) = launch(
        action = {
            if(!validate(email, password))
                return@launch

            val result = authRepository.login(email, password)

            if(result.isSuccess) {
                _uiState.emit(LoginUiState.LoginSuccessful)

            } else if(result.isFailure) {
                _uiState.emit(
                    LoginUiState.ShowError(result.error().message.orEmpty())
                )
            }
        },
        onError = {
            _uiState.emit(
                LoginUiState.ShowError(it.message.orEmpty())
            )
        },
        dispatcher = dispatchers.io()
    )

    private suspend fun validate(
        email: String,
        password: String
    ): Boolean {
        if(!validateEmail(email)) {
            _uiState.emit(LoginUiState.InvalidEmail)
            return false
        }
        if(!validatePassword(password)) {
            _uiState.emit(LoginUiState.InvalidPassword)
            return false
        }

        return true
    }

    private fun validateEmail(email: String): Boolean {
        return resourceManager.validateEmail(email)
    }

    private fun validatePassword(password: String): Boolean {
        return password.count() > 6
    }
}