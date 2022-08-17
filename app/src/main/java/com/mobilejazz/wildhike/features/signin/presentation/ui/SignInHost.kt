package com.mobilejazz.wildhike.features.signin.presentation.ui

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilejazz.compose.dialog.ErrorDialog
import com.mobilejazz.compose.keyboard.LocalKeyboardManager
import com.mobilejazz.wildhike.coroutines.collectAsStateValue
import com.mobilejazz.wildhike.features.home.presentation.ui.HomeHost
import com.mobilejazz.wildhike.features.signin.presentation.resource.SignInTexts
import com.mobilejazz.wildhike.features.signin.presentation.viewmodel.SignInError
import com.mobilejazz.wildhike.features.signin.presentation.viewmodel.SignInViewModel
import com.mobilejazz.wildhike.navigation.graph.LocalNavController
import com.mobilejazz.wildhike.navigation.host.BackPressStrategy
import com.mobilejazz.wildhike.navigation.host.Host
import com.mobilejazz.wildhike.navigation.host.HostType

val SignInHost = Host(
    route = "SignIn",
    title = "Sign In",
    type = HostType.MAIN,
    backPressStrategy = BackPressStrategy.EXIT_APPLICATION
)

fun NavController.navigateToLogin() =
    navigate(SignInHost.route) {
        popUpTo(SignInHost.route) { inclusive = true }
    }

@Composable
fun SignInHost(viewModel: SignInViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateValue()
    val navController = LocalNavController.current

    val emailError = viewModel.emailInput.collectErrorState()
    val passwordError = viewModel.passwordInput.collectErrorState()
    var isLoading by remember { mutableStateOf(false) }
    var signInErrorMessage by remember { mutableStateOf<String?>(null) }
    var networkErrorMessage by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(state) {
        if (true == state?.isLoggedIn) navController.navigate(HomeHost.route)
        isLoading = state?.isLoading ?: false

        when (val error = state?.error) {
            is SignInError.NetworkError -> networkErrorMessage = error.message
            is SignInError.WrongCredentials -> signInErrorMessage = error.message
            else -> {
                networkErrorMessage = null
                signInErrorMessage = null
            }
        }
    }

    SignInScreen(
        isLoading = isLoading,
        emailError = emailError,
        passwordError = passwordError,
        signInError = signInErrorMessage,
        onEmailChange = { viewModel.onEmailChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        onSignInRequest = { viewModel.onSignInRequest() },
        keyboardManager = LocalKeyboardManager.current
    )

    networkErrorMessage?.let {
        ErrorDialog(
            title = SignInTexts.NETWORK_ERROR_TITLE,
            message = it,
            onClosed = { viewModel.resetState() }
        )
    }
}