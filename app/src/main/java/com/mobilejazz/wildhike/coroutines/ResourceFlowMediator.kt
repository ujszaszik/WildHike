package com.mobilejazz.wildhike.coroutines

import androidx.lifecycle.viewModelScope
import com.mobilejazz.network.ApiErrorCodes
import com.mobilejazz.network.call.Resource
import com.mobilejazz.reducer.ReducerViewModel
import com.mobilejazz.reducer.model.UiEvent
import com.mobilejazz.reducer.model.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

typealias ResourceFlow<T> = Flow<Resource<T>>

class ResourceFlowMediator<Source, State : UiState, Event : UiEvent>(
    private val source: ResourceFlow<Source>,
    private val viewModel: ReducerViewModel<State, Event>,
    private val loadEvent: () -> Event,
    private val successEvent: (Source) -> Event,
    private val errorEvent: (String) -> Event
) {

    fun begin() {
        viewModel.viewModelScope.launch {
            source.collect { resource ->
                when (resource.status) {
                    Resource.Status.LOADING -> doOnLoading()
                    Resource.Status.SUCCESS -> doOnSuccess(resource)
                    Resource.Status.ERROR -> doOnError(resource)
                }
            }
        }

    }

    private fun doOnLoading() = viewModel.launch { viewModel.reducer.sendEvent(loadEvent()) }

    private fun doOnSuccess(resource: Resource<Source>) {
        viewModel.launch {
            resource.data?.let {
                viewModel.reducer.sendEvent(successEvent(it))
            }
        }
    }

    private fun doOnError(resource: Resource<Source>) {
        viewModel.launch {
            val errorMessage = resource.errorMessage ?: ApiErrorCodes.DEFAULT.message
            viewModel.reducer.sendEvent(errorEvent(errorMessage))
        }
    }
}