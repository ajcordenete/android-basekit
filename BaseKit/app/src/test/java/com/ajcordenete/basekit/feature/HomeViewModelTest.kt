package com.ajcordenete.basekit.feature

import com.ajcordenete.basekit.core.BaseViewModelTest
import com.ajcordenete.basekit.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class HomeViewModelTest: BaseViewModelTest() {

    private lateinit var subject: HomeViewModel

    /*
    private val repository: UserRepository = Mockito.mock(UserRepository::class.java)

    @Before
    fun setup() {
        subject = HomeViewModel()
    }

    @Test
    fun getUsers_shouldEmitShowUsers_whenResultIsSuccess() = scope.runTest {
        val users = Stubs.USERS

        val expectedState = HomeUIState.ShowUsers(users)
        val result = Result.success(users)

        whenever(repository.getUsers()).thenReturn(result)

        subject.uiState.testSharedFlow {
            subject.getUsers()

            Assert.assertEquals(expectedState, expectItem())
        }
    }

    @Test
    fun getUsers_shouldEmitShowError_whenRequestThrowsException() = scope.runTest {
        val errorMessage = Stubs.ERROR_MESSAGE
        val exception = RuntimeException(errorMessage)

        val expectedState = HomeUIState.ShowError(errorMessage)

        whenever(repository.getUsers()).thenThrow(exception)

        subject.uiState.testSharedFlow {
            subject.getUsers()

            Assert.assertEquals(expectedState, expectItem())
        }
    }*/
}