package com.ajcordenete.domain.core

import kotlinx.coroutines.CoroutineDispatcher

interface BaseDispatcherProvider {
    fun default(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun unconfined(): CoroutineDispatcher

    fun main(): CoroutineDispatcher
}