package com.ajcordenete.basekit.feature

import app.cash.turbine.test
import com.ajcordenete.basekit.Stubs
import com.ajcordenete.basekit.core.BaseViewModelTest
import com.ajcordenete.basekit.core.testSharedFlow
import com.ajcordenete.basekit.home.HomeViewModel
import com.ajcordenete.basekit.user.UserUiState
import com.ajcordenete.basekit.user.UserViewModel
import com.ajcordenete.data.feature.user.UserRepository
import com.ajcordenete.domain.utils.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.Flow
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class UserViewModelTest: BaseViewModelTest() {

    private lateinit var subject: UserViewModel

    private val repository: UserRepository = Mockito.mock(UserRepository::class.java)

    @Before
    fun setup() {
        subject = UserViewModel(repository)
    }

    @Test
    fun getUsers_shouldEmitShowUsers_whenResultIsSuccess() = scope.runTest {
        val users = Stubs.USERS

        val expectedState = UserUiState.ShowUsers(users)
        val result = Result.success(users)

        whenever(repository.updateUsersFromRemote()).thenReturn(result)

        subject.uiState.testSharedFlow {
            subject.getUsers()

            Assert.assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun getUsers_shouldEmitShowError_whenRequestThrowsException() = scope.runTest {
        val errorMessage = Stubs.ERROR_MESSAGE
        val exception = RuntimeException(errorMessage)

        val expectedState = UserUiState.ShowError(errorMessage)

        whenever(repository.updateUsersFromRemote()).thenThrow(exception)

        subject.uiState.testSharedFlow {
            subject.getUsers()

            Assert.assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun getUsersFlow_shouldEmitShowUsers_whenResultIsSuccess() = scope.runTest {
        val users = Stubs.USERS

        val expectedState = UserUiState.ShowUsers(users)
        val result = flowOf(users)

        whenever(repository.getUsersFlow()).thenReturn(result)

        subject.uiState.testSharedFlow {
            subject.getUsersFlow()

            Assert.assertEquals(expectedState, awaitItem())
        }
    }
}