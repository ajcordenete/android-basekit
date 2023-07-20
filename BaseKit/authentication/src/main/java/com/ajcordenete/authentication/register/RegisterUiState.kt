package com.ajcordenete.authentication.register

sealed class RegisterUiState {

    object RegisterSuccessful: RegisterUiState()

    object InvalidName: RegisterUiState()

    object InvalidEmail: RegisterUiState()

    object InvalidPassword: RegisterUiState()

    object PasswordsDoesNotMatch: RegisterUiState()

    data class ShowError(val message: String): RegisterUiState()
}