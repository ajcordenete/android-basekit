package com.ajcordenete.authentication.login

import com.ajcordenete.domain.models.User

sealed class LoginUiState {

    object LoginSuccessful: LoginUiState()

    object InvalidEmail: LoginUiState()

    object InvalidPassword: LoginUiState()

    data class ShowError(val message: String): LoginUiState()
}