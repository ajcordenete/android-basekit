package com.ajcordenete.basekit.landing

sealed class LandingState {
        object UserIsLoggedIn: LandingState()

        object UserIsNotLoggedIn: LandingState()

        data class ShowError(val message: String): LandingState()
}