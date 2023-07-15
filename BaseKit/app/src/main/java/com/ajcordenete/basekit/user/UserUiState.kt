package com.ajcordenete.basekit.user

import com.ajcordenete.domain.models.User

sealed class UserUiState {

    data class ShowUsers(val users: List<User>): UserUiState()

    data class ShowError(val message: String): UserUiState()
}