package com.mobilejazz.wildhike.features.main.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.mobilejazz.compose.keyboard.KeyboardManager
import com.mobilejazz.compose.keyboard.LocalKeyboardManager
import com.mobilejazz.wildhike.coroutines.collectAsStateValue
import com.mobilejazz.wildhike.features.main.viewmodel.MainViewModel
import com.mobilejazz.wildhike.navigation.graph.LocalNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val keyboardManager = KeyboardManager(this)

            val state = viewModel.state.collectAsStateValue()

            LaunchedEffect(state) {
                if (true == state?.isExitRequested) finishAffinity().run { viewModel.resetState() }
            }

            CompositionLocalProvider(
                LocalNavController provides navController,
                LocalKeyboardManager provides keyboardManager
            ) { MainHost(viewModel) }
        }
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

}