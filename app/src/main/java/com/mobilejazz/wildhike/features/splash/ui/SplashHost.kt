package com.mobilejazz.wildhike.features.splash.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilejazz.wildhike.coroutines.collectAsStateValue
import com.mobilejazz.wildhike.features.signin.presentation.ui.navigateToLogin
import com.mobilejazz.wildhike.features.splash.viewmodel.SplashViewModel
import com.mobilejazz.wildhike.navigation.graph.LocalNavController
import com.mobilejazz.wildhike.navigation.host.Host

val SplashHost = Host(route = "SplashHost")

@Composable
fun SplashHost(viewModel: SplashViewModel = hiltViewModel()) {

    val viewModelState = viewModel.state.collectAsStateValue()

    viewModelState?.let { state ->
        if (!state.isLoading) {
            LocalNavController.current.navigateToLogin()
        }
    }

    SplashScreen()
}