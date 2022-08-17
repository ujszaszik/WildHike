package com.mobilejazz.wildhike.features.splash.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import com.mobilejazz.reducer.ReducerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ReducerViewModel<SplashState, SplashEvent>() {

    override val reducer = SplashReducer(SplashState())

    init {
        makeSplashDelay()
    }

    private fun makeSplashDelay() {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            reducer.sendEvent(SplashEvent.NavigateToSignIn)
        }
    }

    companion object {
        @VisibleForTesting
        const val SPLASH_DELAY = 1500L
    }
}