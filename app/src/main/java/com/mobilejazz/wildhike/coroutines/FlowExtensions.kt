package com.mobilejazz.wildhike.coroutines

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


fun ViewModel.launch(block: suspend () -> Unit) {
    viewModelScope.launch { block() }
}

fun <T> mutableStateFlow() = MutableStateFlow<T?>(null)

@Composable
fun <T> Flow<T>.collectAsStateValue(): T? {
    return collectAsState(initial = null).value
}