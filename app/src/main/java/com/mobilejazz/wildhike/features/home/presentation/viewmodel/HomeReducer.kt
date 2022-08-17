package com.mobilejazz.wildhike.features.home.presentation.viewmodel

import com.mobilejazz.reducer.Reducer
import com.mobilejazz.reducer.model.UiEvent
import com.mobilejazz.reducer.model.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class HomeState(
    val isLoading: Boolean = false,
    val username: String? = null,
    val needsToApproveSignOut: Boolean = false,
    val isLoggedOut: Boolean = false
) : UiState

@Immutable
sealed class HomeEvent : UiEvent {
    object Loading : HomeEvent()
    object ResetState : HomeEvent()
    class UsernameLoaded(val username: String) : HomeEvent()
    object ShowApprovalDialog : HomeEvent()
    object NavigateToSignIn : HomeEvent()
}

class HomeReducer(initialState: HomeState) : Reducer<HomeState, HomeEvent>(initialState) {

    override fun reduce(oldState: HomeState, event: HomeEvent) {
        when (event) {

            HomeEvent.Loading -> {
                setState(oldState.copy(isLoading = true))
            }

            HomeEvent.ResetState -> {
                setState(oldState.copy(needsToApproveSignOut = false))
            }

            is HomeEvent.UsernameLoaded -> {
                setState(oldState.copy(isLoading = false, username = event.username))
            }

            HomeEvent.ShowApprovalDialog -> {
                setState(oldState.copy(needsToApproveSignOut = true))
            }

            HomeEvent.NavigateToSignIn -> {
                setState(oldState.copy(needsToApproveSignOut = false, isLoggedOut = true))
            }

        }
    }

}