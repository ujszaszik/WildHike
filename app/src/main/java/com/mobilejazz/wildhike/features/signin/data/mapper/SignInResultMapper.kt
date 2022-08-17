package com.mobilejazz.wildhike.features.signin.data.mapper

import com.mobilejazz.network.mapper.BaseMapper
import com.mobilejazz.wildhike.features.signin.data.datasource.remote.SignInResponse
import com.mobilejazz.wildhike.features.signin.data.model.SignInResult

class SignInResultMapper : BaseMapper<SignInResponse, SignInResult> {

    override fun map(remote: SignInResponse): SignInResult {
        return SignInResult(remote.token)
    }
}