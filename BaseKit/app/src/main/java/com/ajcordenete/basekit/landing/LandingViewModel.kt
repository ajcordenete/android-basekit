package com.ajcordenete.basekit.landing

import android.os.Bundle
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.core.ext.launch
import com.ajcordenete.data.core.isEmpty
import com.ajcordenete.data.feature.session.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
): BaseViewModel() {

    private val _uiState = MutableSharedFlow<LandingState>()
    val uiState = _uiState.asSharedFlow()

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

    fun checkSession() = launch(
        action = {
            val session = sessionRepository.getSession()

            if(session.isEmpty()) {
                _uiState.emit(LandingState.UserIsNotLoggedIn)
            } else {
                _uiState.emit(LandingState.UserIsLoggedIn)
            }
        },
        onError = {
            _uiState.emit(LandingState.ShowError(it.message.orEmpty()))
        }
    )
}