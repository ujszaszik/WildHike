package com.mobilejazz.wildhike.features.home.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobilejazz.wildhike.features.home.data.repository.FakeHomeRepository
import com.mobilejazz.wildhike.features.home.data.usecase.DeleteCurrentUserTokenUseCase
import com.mobilejazz.wildhike.features.home.data.usecase.GetCurrentUserNameUseCase
import com.mobilejazz.wildhike.rules.CoroutineTestRule
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule(order = 1)
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 2)
    var coroutineTestRule = CoroutineTestRule()

    private fun getViewModel(returnErrorValues: Boolean = false): HomeViewModel {
        val fakeRepository = FakeHomeRepository().apply { returnError = returnErrorValues }
        return HomeViewModel(
            GetCurrentUserNameUseCase(fakeRepository),
            DeleteCurrentUserTokenUseCase(fakeRepository)
        )
    }


    @Test
    fun `test viewmodel state, when loading event sent, then reducer state is updated`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(HomeEvent.Loading)
            val state = viewModel.reducer.state.first()
            assertTrue(state.isLoading)
        }
    }

    @Test
    fun `test viewmodel state, when sign out request event sent, then reducer state is updated`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.onSignOutRequest()
            val state = viewModel.reducer.state.first()
            assertTrue(state.needsToApproveSignOut)
        }
    }

    @Test
    fun `test viewmodel state, when sign out request approved event sent, then reducer state is updated`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.onSignOutApproved()
            val state = viewModel.reducer.state.first()
            assertTrue(state.isLoggedOut)
        }
    }

    @Test
    fun `test viewmodel state, when sign out declined event sent, then reducer state is reset`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.onSignOutDeclined()
            val state = viewModel.reducer.state.first()
            assertTrue(state == HomeState())
        }
    }

    @Test
    fun `test viewmodel state, when username loaded event sent, then reducer state is updated with username`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(HomeEvent.UsernameLoaded(FakeHomeRepository.TEST_USERNAME))
            val state = viewModel.reducer.state.first()
            assertTrue(state.username == FakeHomeRepository.TEST_USERNAME)
        }
    }

    @Test
    fun `test viewmodel state, when username loaded event sent, then reducer state is updated with not loading`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(HomeEvent.UsernameLoaded(FakeHomeRepository.TEST_USERNAME))
            val state = viewModel.reducer.state.first()
            assertFalse(state.isLoading)
        }
    }

    @Test
    fun `test viewmodel state, when navigate to sign in event sent, then reducer state is updated with no need to approve sign out`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(HomeEvent.NavigateToSignIn)
            val state = viewModel.reducer.state.first()
            assertFalse(state.needsToApproveSignOut)
        }
    }

    @Test
    fun `test viewmodel state, when navigate to sign in event sent, then reducer state is updated with logged out`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(HomeEvent.NavigateToSignIn)
            val state = viewModel.reducer.state.first()
            assertTrue(state.isLoggedOut)
        }
    }


}