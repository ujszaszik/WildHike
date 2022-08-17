package com.mobilejazz.wildhike.features.signin.data.usecase

import com.mobilejazz.wildhike.features.signin.data.repository.ISignInRepository
import javax.inject.Inject

class SaveUserTokenUseCase @Inject constructor(
    private val repository: ISignInRepository
) {

    operator fun invoke(token: String) = repository.saveUserToken(token)
}