package com.mobilejazz.wildhike.features.main.viewmodel

import com.mobilejazz.reducer.ReducerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ReducerViewModel<MainState, MainEvent>() {

    override val reducer = MainReducer(MainState())

    internal fun onBackPressed() = reducer.sendEvent(MainEvent.BackPressed)

    internal fun onExitRequest() = reducer.sendEvent(MainEvent.ExitRequested)

    internal fun resetState() = reducer.sendEvent(MainEvent.ResetState)
}