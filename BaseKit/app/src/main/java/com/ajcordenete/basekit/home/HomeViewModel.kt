package com.ajcordenete.basekit.home

import android.os.Bundle
import com.ajcordenete.basekit.user.UserUiState
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.core.ext.launch
import com.ajcordenete.data.feature.auth.AuthRepository
import com.ajcordenete.data.feature.user.UserRepository
import com.ajcordenete.domain.error
import com.ajcordenete.domain.get
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

    fun login() = launch(
        action = {
            val result = authRepository.login("jk@gmail.com", "password")
            if(result.isSuccess) {
                Timber.i("auth: ${result.get()}")
            } else if(result.isFailure) {
                Timber.i("auth failed: ${result.error()}")
            }
        },
        onError = {
            Timber.e("auth failed: ${it.message.orEmpty()}")
        }
    )

}