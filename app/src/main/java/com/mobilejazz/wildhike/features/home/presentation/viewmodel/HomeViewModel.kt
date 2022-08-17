package com.mobilejazz.wildhike.features.home.presentation.viewmodel

import com.mobilejazz.reducer.ReducerViewModel
import com.mobilejazz.wildhike.coroutines.ResourceFlowMediator
import com.mobilejazz.wildhike.features.home.data.usecase.DeleteCurrentUserTokenUseCase
import com.mobilejazz.wildhike.features.home.data.usecase.GetCurrentUserNameUseCase
import com.mobilejazz.wildhike.features.home.presentation.resource.HomeTexts
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentUserNameUseCase: GetCurrentUserNameUseCase,
    private val deleteCurrentUserTokenUseCase: DeleteCurrentUserTokenUseCase
) : ReducerViewModel<HomeState, HomeEvent>() {

    override val reducer = HomeReducer(HomeState())

    init {
        loadUserName()
    }

    private fun loadUserName() {
        ResourceFlowMediator(
            source = getCurrentUserNameUseCase(),
            viewModel = this,
            loadEvent = { HomeEvent.Loading },
            successEvent = { HomeEvent.UsernameLoaded(it.username) },
            errorEvent = { HomeEvent.UsernameLoaded(HomeTexts.NAME_ANONYMOUS_LABEL) }
        ).begin()
    }

    fun onSignOutRequest() = reducer.sendEvent(HomeEvent.ShowApprovalDialog)

    fun onSignOutApproved() {
        deleteCurrentUserTokenUseCase()
        reducer.sendEvent(HomeEvent.NavigateToSignIn)
    }

    fun onSignOutDeclined() = reducer.sendEvent(HomeEvent.ResetState)
}