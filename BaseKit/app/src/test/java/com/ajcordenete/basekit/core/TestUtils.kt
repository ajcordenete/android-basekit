package com.ajcordenete.basekit.core

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import kotlinx.coroutines.flow.SharedFlow
import kotlin.time.ExperimentalTime

/**
 * Utility method that calls `cancelAndConsumeRemainingEvents()` automatically after the [testBody].
 */
@ExperimentalTime
suspend fun <T : Any> SharedFlow<T>.testSharedFlow(testBody: suspend TurbineTestContext<T>.() -> Unit) {
    test {
        testBody(this)
        cancelAndConsumeRemainingEvents()
    }
}
