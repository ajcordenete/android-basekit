package com.ajcordenete.basekit.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseViewModelTest {
    @get:Rule
    val executor = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    val scope = TestScope(dispatcher)

    @Before
    fun baseSetUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun baseTearDown() {
        Dispatchers.resetMain()
    }
}