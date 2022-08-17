package com.mobilejazz.wildhike.features.main.viewmodel

import com.mobilejazz.reducer.Reducer
import com.mobilejazz.reducer.model.UiEvent
import com.mobilejazz.reducer.model.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class MainState(
    val isBackPressed: Boolean = false,
    val isExitRequested: Boolean = false
) : UiState

@Immutable
sealed class MainEvent : UiEvent {
    object BackPressed : MainEvent()
    object ExitRequested : MainEvent()
    object ResetState : MainEvent()
}

class MainReducer(initialState: MainState) : Reducer<MainState, MainEvent>(initialState) {

    override fun reduce(oldState: MainState, event: MainEvent) {
        when (event) {

            is MainEvent.BackPressed -> {
                setState(oldState.copy(isBackPressed = true))
            }

            is MainEvent.ExitRequested -> {
                setState(oldState.copy(isExitRequested = true))
            }

            is MainEvent.ResetState -> {
                setState(MainState())
            }

        }
    }
}