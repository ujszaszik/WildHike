package com.mobilejazz.reducer

import com.mobilejazz.reducer.model.UiEvent
import com.mobilejazz.reducer.model.UiState
import com.mobilejazz.reducer.timemachine.TimeCapsule
import com.mobilejazz.reducer.timemachine.TimeTravelCapsule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<S : UiState, E : UiEvent>(initialValue: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialValue)

    val state: StateFlow<S>
        get() = _state

    val timeCapsule: TimeCapsule<S> = TimeTravelCapsule { storedState ->
        _state.tryEmit(storedState)
    }

    init {
        timeCapsule.addState(initialValue)
    }

    fun sendEvent(event: E) {
        reduce(_state.value, event)
    }

    fun setState(newState: S) {
        val success = _state.tryEmit(newState)

        if (BuildConfig.DEBUG && success) {
            timeCapsule.addState(newState)
        }
    }

    abstract fun reduce(oldState: S, event: E)
}