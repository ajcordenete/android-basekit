package com.ajcordenete.authentication.register

import android.os.Bundle
import com.ajcordenete.authentication.login.LoginUiState
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
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val resourceManager: ResourceManager
): BaseViewModel() {

    private val _uiState = MutableSharedFlow<RegisterUiState>()
    val uiState = _uiState.asSharedFlow()

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

    fun register(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) = launch(
        action = {
            if(!validate(fullName, email, password, confirmPassword))
                return@launch

            _uiState.emit(RegisterUiState.ShowLoading)

            val result = authRepository
                .register(
                    email = email,
                    password = password,
                    fullName = fullName,
                    firstName = "",
                    lastName = ""
                )

            if(result.isSuccess) {
                _uiState.emit(RegisterUiState.RegisterSuccessful)

            } else if(result.isFailure) {
                _uiState.emit(
                    RegisterUiState.ShowError(result.error().message.orEmpty())
                )
            }
            _uiState.emit(RegisterUiState.HideLoading)
        },
        onError = {
            _uiState.emit(RegisterUiState.HideLoading)
            _uiState.emit(
                RegisterUiState.ShowError(it.message.orEmpty())
            )
        },
        dispatcher = dispatchers.io()
    )

    private suspend fun validate(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if(!validateName(name)) {
            _uiState.emit(RegisterUiState.InvalidName)
            return false
        }
        if(!validateEmail(email)) {
            _uiState.emit(RegisterUiState.InvalidEmail)
            return false
        }
        if(!validatePassword(password)) {
            _uiState.emit(RegisterUiState.InvalidPassword)
            return false
        }
        if(!validateMatchingPassword(password, confirmPassword)) {
            _uiState.emit(RegisterUiState.PasswordsDoesNotMatch)
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

    private fun validateName(name: String): Boolean {
        return name.isNotEmpty()
    }

    private fun validateMatchingPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}