package com.mobilejazz.wildhike.features.splash.presentation.viewmodel


import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobilejazz.wildhike.features.splash.viewmodel.SplashViewModel
import com.mobilejazz.wildhike.rules.CoroutineTestRule
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @get:Rule(order = 1)
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 2)
    var coroutineTestRule = CoroutineTestRule()

    private fun getViewModel() = SplashViewModel()

    @Test
    fun `test viewmodel state, when initialized, then state is loading`() {
        runTest {
            val viewModel = getViewModel()
            val state = viewModel.reducer.state.first()
            assertTrue(state.isLoading)
        }
    }

    @Test
    fun `test viewmodel state, when splash delay finishes, then state is not loading`() {
        runTest {
            val viewModel = getViewModel()
            delay(SplashViewModel.SPLASH_DELAY)
            val state = viewModel.reducer.state.first()
            assertFalse(state.isLoading)
        }
    }
}
