package com.ajcordenete.authentication.register

sealed class RegisterUiState {

    object RegisterSuccessful: RegisterUiState()

    object InvalidName: RegisterUiState()

    object InvalidEmail: RegisterUiState()

    object InvalidPassword: RegisterUiState()

    object PasswordsDoesNotMatch: RegisterUiState()

    object ShowLoading: RegisterUiState()

    object HideLoading: RegisterUiState()

    data class ShowError(val message: String): RegisterUiState()
}