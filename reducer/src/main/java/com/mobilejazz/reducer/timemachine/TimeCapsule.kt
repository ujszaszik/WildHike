package com.mobilejazz.reducer.timemachine

import com.mobilejazz.reducer.model.UiState

interface TimeCapsule<S : UiState> {
    fun addState(state: S)
    fun selectState(position: Int)
    fun getStates(): List<S>
}