package com.ajcordenete.core.ext

import androidx.lifecycle.viewModelScope
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.domain.core.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Launches [action] inside [viewModelScope].
 *
 * @param dispatcher dispatcher to launch this coroutine from. Defaults to [BaseViewModel.dispatchers.main()].
 */
fun BaseViewModel.launch(
    dispatcher: CoroutineContext = DispatcherProvider.main(),
    action: suspend CoroutineScope.() -> Unit,
    onError: suspend CoroutineScope.(e: Exception) -> Unit = {}
) {
    viewModelScope.launch(dispatcher) {
        try {
            action(this)
        } catch (e: Exception) {
            onError(this, e)
        }
    }
}