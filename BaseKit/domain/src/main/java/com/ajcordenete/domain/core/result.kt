package com.ajcordenete.domain

fun <T : Any> Result<T>.get(): T {
    return this.getOrNull()!!
}

fun <T : Any> Result<T>.error(): Error {
    return this.exceptionOrNull()!! as Error
}