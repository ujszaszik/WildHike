package com.mobilejazz.wildhike.features.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.mobilejazz.compose.keyboard.keyboardAsState
import com.mobilejazz.compose.resources.Colors
import com.mobilejazz.wildhike.coroutines.collectAsStateValue
import com.mobilejazz.wildhike.features.main.viewmodel.MainViewModel
import com.mobilejazz.wildhike.navigation.graph.ApplicationGraph
import com.mobilejazz.wildhike.navigation.graph.LocalNavController
import com.mobilejazz.wildhike.navigation.host.BackPressStrategy
import com.mobilejazz.wildhike.navigation.host.Host
import com.mobilejazz.wildhike.navigation.host.extractHost

@Composable
fun MainHost(viewModel: MainViewModel) {

    val navController = LocalNavController.current

    var host by remember { mutableStateOf<Host?>(null) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        host = destination.label.toString().extractHost()
    }

    val state = viewModel.state.collectAsStateValue()

    LaunchedEffect(state) {
        if (true == state?.isBackPressed) {
            when (host?.backPressStrategy) {
                BackPressStrategy.POP_BACKSTACK -> navController.popBackStack()
                BackPressStrategy.EXIT_APPLICATION -> viewModel.onExitRequest()
                else -> Unit
            }
            viewModel.resetState()
        }
    }

    val isKeyboardOpened = keyboardAsState().value.isOpened()

    var scaffoldModifier = Modifier.statusBarsPadding()
    if (!isKeyboardOpened) scaffoldModifier = scaffoldModifier.navigationBarsWithImePadding()

    ProvideWindowInsets {
        Scaffold(
            modifier = scaffoldModifier,
            content = {
                Column(
                    modifier = Modifier
                        .background(Colors.appBlue)
                        .fillMaxSize()
                        .imePadding()
                ) {
                    ApplicationGraph()
                }
            },
        )
    }
}