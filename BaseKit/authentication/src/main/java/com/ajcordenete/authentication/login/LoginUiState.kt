package com.ajcordenete.authentication.login

import com.ajcordenete.domain.models.User

sealed class LoginUiState {

    object LoginSuccessful: LoginUiState()

    object InvalidEmail: LoginUiState()

    object InvalidPassword: LoginUiState()

    object ShowLoading: LoginUiState()

    object HideLoading: LoginUiState()

    data class ShowError(val message: String): LoginUiState()
}