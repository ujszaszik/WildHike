package com.mobilejazz.wildhike.features.signin.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobilejazz.wildhike.features.signin.data.repository.FakeSignInRepository
import com.mobilejazz.wildhike.features.signin.data.usecase.GetIfUserAlreadyLoggedInUseCase
import com.mobilejazz.wildhike.features.signin.data.usecase.SaveUserTokenUseCase
import com.mobilejazz.wildhike.features.signin.data.usecase.SignInUseCase
import com.mobilejazz.wildhike.features.signin.presentation.resource.SignInTexts
import com.mobilejazz.wildhike.rules.CoroutineTestRule
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SignInViewModelTest {

    @get:Rule(order = 1)
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 2)
    var coroutineTestRule = CoroutineTestRule()

    private fun getViewModel(fakeRepository: FakeSignInRepository = FakeSignInRepository()) =
        SignInViewModel(
            SignInUseCase(fakeRepository),
            SaveUserTokenUseCase(fakeRepository),
            GetIfUserAlreadyLoggedInUseCase(fakeRepository)
        )

    @Test
    fun `test viewmodel state, when initialized and signed in, then authenticated event is sent`() {
        runTest {
            val fakeRepository = FakeSignInRepository().apply { setAsSignedIn() }
            val viewModel = getViewModel(fakeRepository)
            val state = viewModel.reducer.state.first()
            assertTrue(state.isLoggedIn)
        }
    }

    @Test
    fun `test viewmodel state, when initialized and not signed in, then authenticated event is not sent`() {
        runTest {
            val viewModel = getViewModel()
            val state = viewModel.reducer.state.first()
            assertFalse(state.isLoggedIn)
        }
    }

    @Test
    fun `test email input, when initialized, then value is empty`() {
        runTest {
            val viewModel = getViewModel()
            assertTrue(viewModel.emailInput.actualValue().isEmpty())
        }
    }

    @Test
    fun `test email input, when value change request from view model, then input value is updated appropriately`() {
        runTest {
            val viewModel = getViewModel()
            val testEmail = "TEST EMAIL"
            viewModel.onEmailChange(testEmail)
            assertTrue(testEmail == viewModel.emailInput.actualValue())
        }
    }

    @Test
    fun `test email input, when there is no value, then input flow returns invalid`() {
        runBlocking {
            val viewModel = getViewModel()
            assertFalse(viewModel.emailInput.isValid())
        }
    }

    @Test
    fun `test email input, when value change requested from view model with invalid email address, then input flow returns invalid`() {
        runBlocking {
            val viewModel = getViewModel()
            val invalidEmailAddress = "INVALID EMAIL ADDRESS"
            viewModel.onEmailChange(invalidEmailAddress)
            assertFalse(viewModel.emailInput.isValid())
        }
    }

    @Test
    fun `test email input, when value change requested from view model with valid email address, then input flow returns valid`() {
        runBlocking {
            val viewModel = getViewModel()
            val validEmailAddress = "valid.email@address.com"
            viewModel.onEmailChange(validEmailAddress)
            assertTrue(viewModel.emailInput.isValid())
        }
    }

    @Test
    fun `test password input, when there is no value, then input flow returns invalid`() {
        runBlocking {
            val viewModel = getViewModel()
            assertFalse(viewModel.passwordInput.isValid())
        }
    }

    @Test
    fun `test password input, when value change requested from view model with invalid password, then input flow returns invalid`() {
        runBlocking {
            val viewModel = getViewModel()
            val invalidPassword = "abc123"
            viewModel.onPasswordChange(invalidPassword)
            assertFalse(viewModel.passwordInput.isValid())
        }
    }

    @Test
    fun `test password input, when value change requested from view model with valid password, then input flow returns valid`() {
        runBlocking {
            val viewModel = getViewModel()
            val validPassword = "Test1234!"
            viewModel.onPasswordChange(validPassword)
            assertTrue(viewModel.passwordInput.isValid())
        }
    }

    @Test
    fun `test sign in request, when user email is invalid, then request not called`() {
        runBlocking {
            val viewModel = getViewModel()
            val invalidUserEmail = "INVALID USER EMAIL"
            viewModel.onEmailChange(invalidUserEmail)
            viewModel.onSignInRequest()
            val state = viewModel.reducer.state.first()
            assertFalse(state.isLoading)
        }
    }

    @Test
    fun `test sign in request, when password is invalid, then request not called`() {
        runBlocking {
            val viewModel = getViewModel()
            val invalidPassword = "abc123"
            viewModel.onPasswordChange(invalidPassword)
            viewModel.onSignInRequest()
            val state = viewModel.reducer.state.first()
            assertFalse(state.isLoading)
        }
    }

    @Test
    fun `test viewmodel, when initialized, then state is not loading`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(SignInEvent.Reset)
            val state = viewModel.state.first()
            assertFalse(state.isLoading)
        }
    }

    @Test
    fun `test viewmodel, when initialized, then state has no errors`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(SignInEvent.Reset)
            val state = viewModel.state.first()
            assertNull(state.error)
        }
    }

    @Test
    fun `test viewmodel, when initialized, then state is not logged in`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(SignInEvent.Reset)
            val state = viewModel.state.first()
            assertFalse(state.isLoggedIn)
        }
    }

    @Test
    fun `test viewmodel, when reset event sent, then state is updated`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(SignInEvent.Reset)
            val state = viewModel.state.first()
            assertTrue(state == SignInState())
        }
    }

    @Test
    fun `test viewmodel, when loading event sent, then state is loading`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(SignInEvent.Loading)
            val state = viewModel.state.first()
            assertTrue(state.isLoading)
        }
    }

    @Test
    fun `test viewmodel, when loading event sent, then state has no errors`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(SignInEvent.Loading)
            val state = viewModel.state.first()
            assertNull(state.error)
        }
    }

    @Test
    fun `test viewmodel, when authenticated event sent, then state is logged in`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(SignInEvent.Authenticated)
            val state = viewModel.state.first()
            assertTrue(state.isLoggedIn)
        }
    }

    @Test
    fun `test viewmodel, when authenticated event sent, then state is not loading`() {
        runTest {
            val viewModel = getViewModel()
            viewModel.reducer.sendEvent(SignInEvent.Authenticated)
            val state = viewModel.state.first()
            assertFalse(state.isLoading)
        }
    }

    @Test
    fun `test viewmodel, when error event sent, then state is updated appropriately`() {
        runTest {
            val viewModel = getViewModel()
            val errorMessage = SignInTexts.NETWORK_ERROR_TITLE
            val error = SignInError.NetworkError(errorMessage)
            viewModel.reducer.sendEvent(SignInEvent.Error(error))
            val state = viewModel.state.first()
            assertTrue(state.error == error)
        }
    }

    @Test
    fun `test viewmodel, when error event sent, then state is not loading`() {
        runTest {
            val viewModel = getViewModel()
            val errorMessage = SignInTexts.NETWORK_ERROR_TITLE
            viewModel.reducer.sendEvent(SignInEvent.Error(SignInError.NetworkError(errorMessage)))
            val state = viewModel.state.first()
            assertFalse(state.isLoading)
        }
    }
}