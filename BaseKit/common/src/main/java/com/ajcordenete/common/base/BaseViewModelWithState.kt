package com.ajcordenete.common.base

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModelWithState<T>: BaseViewModel() {

    protected val _uiState = MutableSharedFlow<T>()
    val uiState = _uiState.asSharedFlow()
}