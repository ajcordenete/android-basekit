package com.ajcordenete.domain

import com.ajcordenete.domain.core.Error

fun <T : Any> Result<T>.get(): T {
    return this.getOrNull()!!
}

fun <T : Any> Result<T>.error(): Error {
    return this.exceptionOrNull()!! as Error
}