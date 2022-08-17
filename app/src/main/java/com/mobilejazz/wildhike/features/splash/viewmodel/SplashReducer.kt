package com.mobilejazz.wildhike.features.splash.viewmodel

import com.mobilejazz.reducer.Reducer
import com.mobilejazz.reducer.model.UiEvent
import com.mobilejazz.reducer.model.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class SplashState(
    val isLoading: Boolean = true
) : UiState

@Immutable
sealed class SplashEvent : UiEvent {
    object NavigateToSignIn : SplashEvent()
}

class SplashReducer(initialState: SplashState) :
    Reducer<SplashState, SplashEvent>(initialState) {

    override fun reduce(oldState: SplashState, event: SplashEvent) {
        when (event) {
            is SplashEvent.NavigateToSignIn -> {
                setState(oldState.copy(isLoading = false))
            }
        }
    }
}