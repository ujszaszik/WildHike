package com.mobilejazz.wildhike.features.home.data.repository

import com.mobilejazz.network.call.Resource
import com.mobilejazz.wildhike.coroutines.ResourceFlow
import com.mobilejazz.wildhike.features.home.data.model.UserInfo
import kotlinx.coroutines.flow.flow

class FakeHomeRepository : IHomeRepository {
    var token: String? = TEST_TOKEN
    var returnError: Boolean = false

    override fun deleteCurrentUserToken() {
        token = null
    }

    override fun getCurrentUserName(): ResourceFlow<UserInfo> {
        return if (returnError) flow { Resource.error<UserInfo>() }
        else flow { Resource.success(UserInfo(TEST_USERNAME)) }
    }

    override fun getCurrentUserToken() = token

    companion object {
        const val TEST_TOKEN = "Test Token"
        const val TEST_USERNAME = "Test User"
    }
}