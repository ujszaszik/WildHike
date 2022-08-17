package com.mobilejazz.wildhike.features.signin.data.usecase

import com.mobilejazz.wildhike.coroutines.ResourceFlow
import com.mobilejazz.wildhike.features.signin.data.model.SignInResult
import com.mobilejazz.wildhike.features.signin.data.repository.ISignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: ISignInRepository
) {

    operator fun invoke(email: String, password: String): ResourceFlow<SignInResult> {
        return repository.performSignIn(email, password)
    }
}