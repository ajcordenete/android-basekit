package com.ajcordenete.domain.utils

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.stubbing.OngoingStubbing

fun anyInt(): Int = Mockito.anyInt()

fun anyString(): String = Mockito.anyString()

fun anyLong(): Long = Mockito.anyLong()

inline fun <reified T> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)

fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

inline fun <reified T> whenever(methodCall: T): OngoingStubbing<T> = `when`(methodCall)