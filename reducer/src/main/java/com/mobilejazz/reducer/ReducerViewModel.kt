package com.mobilejazz.reducer

import androidx.lifecycle.ViewModel
import com.mobilejazz.reducer.model.UiEvent
import com.mobilejazz.reducer.model.UiState
import com.mobilejazz.reducer.timemachine.TimeCapsule
import kotlinx.coroutines.flow.Flow

abstract class ReducerViewModel<S : UiState, E : UiEvent> : ViewModel() {

    abstract val reducer: Reducer<S, E>

    val state: Flow<S>
        get() = reducer.state

    protected val timeCapsule: TimeCapsule<S>
        get() = reducer.timeCapsule
}