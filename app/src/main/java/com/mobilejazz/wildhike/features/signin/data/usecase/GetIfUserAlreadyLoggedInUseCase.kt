package com.mobilejazz.wildhike.features.signin.data.usecase

import com.mobilejazz.wildhike.features.signin.data.repository.ISignInRepository
import javax.inject.Inject

class GetIfUserAlreadyLoggedInUseCase @Inject constructor(
    private val repository: ISignInRepository
) {

    operator fun invoke(): Boolean = repository.isUserLoggedIn()
}