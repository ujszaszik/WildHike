package com.mobilejazz.wildhike.features.signin.presentation.viewmodel

import com.mobilejazz.reducer.Reducer
import com.mobilejazz.reducer.model.UiEvent
import com.mobilejazz.reducer.model.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class SignInState(
    val isLoading: Boolean = false,
    val error: SignInError? = null,
    val isLoggedIn: Boolean = false
) : UiState

@Immutable
sealed class SignInEvent : UiEvent {
    class Error(val value: SignInError) : SignInEvent()
    object Loading : SignInEvent()
    object Authenticated : SignInEvent()
    object Reset : SignInEvent()
}

@Immutable
sealed class SignInError(val message: String) {
    class WrongCredentials(message: String) : SignInError(message)
    class NetworkError(message: String) : SignInError(message)
}

class SignInReducer(initialState: SignInState) : Reducer<SignInState, SignInEvent>(initialState) {

    override fun reduce(oldState: SignInState, event: SignInEvent) {
        when (event) {

            SignInEvent.Reset -> {
                setState(SignInState())
            }

            SignInEvent.Loading -> {
                setState(oldState.copy(isLoading = true, error = null))
            }

            SignInEvent.Authenticated -> {
                setState(oldState.copy(isLoading = false, isLoggedIn = true))
            }

            is SignInEvent.Error -> {
                setState(oldState.copy(isLoading = false, error = event.value))
            }

        }
    }

}