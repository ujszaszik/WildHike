package com.mobilejazz.wildhike.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mobilejazz.wildhike.features.home.presentation.ui.HomeHost
import com.mobilejazz.wildhike.features.signin.presentation.ui.SignInHost
import com.mobilejazz.wildhike.features.splash.ui.SplashHost
import com.mobilejazz.wildhike.navigation.composable

val LocalNavController =
    compositionLocalOf<NavHostController> { error("LocalComposition NavController not present") }

@Composable
fun ApplicationGraph() {

    val navController = LocalNavController.current

    NavHost(navController = navController, startDestination = SplashHost.route) {

        composable(SplashHost) {
            SplashHost()
        }

        composable(SignInHost) {
            SignInHost()
        }

        composable(HomeHost) {
            HomeHost()
        }
    }
}