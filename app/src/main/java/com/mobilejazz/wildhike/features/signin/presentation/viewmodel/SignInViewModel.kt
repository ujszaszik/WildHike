package com.mobilejazz.wildhike.features.signin.presentation.viewmodel

import com.mobilejazz.reducer.ReducerViewModel
import com.mobilejazz.wildhike.coroutines.InputFlow
import com.mobilejazz.wildhike.coroutines.ResourceFlowMediator
import com.mobilejazz.wildhike.features.signin.data.usecase.GetIfUserAlreadyLoggedInUseCase
import com.mobilejazz.wildhike.features.signin.data.usecase.SaveUserTokenUseCase
import com.mobilejazz.wildhike.features.signin.data.usecase.SignInUseCase
import com.mobilejazz.wildhike.features.signin.presentation.validation.EmailValidator
import com.mobilejazz.wildhike.features.signin.presentation.validation.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    isLoggedInUseCase: GetIfUserAlreadyLoggedInUseCase
) : ReducerViewModel<SignInState, SignInEvent>() {

    override val reducer = SignInReducer(SignInState())

    internal var emailInput = InputFlow(EmailValidator)
    internal var passwordInput = InputFlow(PasswordValidator)

    init {
        if (isLoggedInUseCase()) {
            reducer.sendEvent(SignInEvent.Authenticated)
        }
    }

    fun onEmailChange(newValue: String) {
        emailInput.onValueChanged(newValue)
    }

    fun onPasswordChange(newValue: String) {
        passwordInput.onValueChanged(newValue)
    }

    fun onSignInRequest() {
        if (emailInput.isValid() && passwordInput.isValid()) {
            performSignIn()
        }
    }

    private fun performSignIn() {
        ResourceFlowMediator(
            source = signInUseCase(emailInput.actualValue(), passwordInput.actualValue()),
            viewModel = this,
            loadEvent = { SignInEvent.Loading },
            successEvent = {
                saveUserTokenUseCase(it.token)
                SignInEvent.Authenticated
            },
            errorEvent = { SignInEvent.Error(SignInError.WrongCredentials(it)) }
        ).begin()
    }

    fun resetState() = reducer.sendEvent(SignInEvent.Reset)
}