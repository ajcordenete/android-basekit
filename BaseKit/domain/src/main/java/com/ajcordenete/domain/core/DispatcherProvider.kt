package com.ajcordenete.domain.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherProvider : BaseDispatcherProvider {
    override fun default(): CoroutineDispatcher = Dispatchers.Default

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined

    override fun main(): CoroutineDispatcher = Dispatchers.Main
}