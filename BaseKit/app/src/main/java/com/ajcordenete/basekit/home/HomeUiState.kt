package com.ajcordenete.basekit.home

import com.ajcordenete.domain.models.User

sealed class HomeUiState {

    data class ShowUsers(val users: List<User>): HomeUiState()

    data class ShowError(val message: String): HomeUiState()
}