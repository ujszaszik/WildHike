package com.mobilejazz.wildhike.features.home.presentation.ui

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilejazz.compose.dialog.SelectorDialog
import com.mobilejazz.wildhike.coroutines.collectAsStateValue
import com.mobilejazz.wildhike.features.home.presentation.resource.HomeTexts
import com.mobilejazz.wildhike.features.home.presentation.viewmodel.HomeViewModel
import com.mobilejazz.wildhike.features.signin.presentation.ui.navigateToLogin
import com.mobilejazz.wildhike.navigation.graph.LocalNavController
import com.mobilejazz.wildhike.navigation.host.BackPressStrategy
import com.mobilejazz.wildhike.navigation.host.Host
import com.mobilejazz.wildhike.navigation.host.HostType

val HomeHost = Host(
    route = "Home",
    title = "Home",
    type = HostType.MAIN,
    backPressStrategy = BackPressStrategy.EXIT_APPLICATION
)

@Composable
fun HomeHost(viewModel: HomeViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateValue()
    val navController = LocalNavController.current
    var showApprovalDialog by remember { mutableStateOf(false) }

    LaunchedEffect(state) {
        if (true == state?.isLoggedOut) navController.navigateToLogin()
        showApprovalDialog = true == state?.needsToApproveSignOut
    }

    HomeScreen(
        username = state?.username,
        onSignOutRequest = { viewModel.onSignOutRequest() }
    )

    if (showApprovalDialog) {
        SelectorDialog(
            title = HomeTexts.SIGN_OUT_LABEL,
            message = HomeTexts.SIGN_OUT_MESSAGE,
            onOkay = { viewModel.onSignOutApproved() },
            onCancel = { viewModel.onSignOutDeclined() },
        )
    }

}