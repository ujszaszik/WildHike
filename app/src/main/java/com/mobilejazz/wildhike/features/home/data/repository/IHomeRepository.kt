package com.mobilejazz.wildhike.features.home.data.repository

import com.mobilejazz.wildhike.coroutines.ResourceFlow
import com.mobilejazz.wildhike.features.home.data.model.UserInfo

interface IHomeRepository {

    fun getCurrentUserName(): ResourceFlow<UserInfo>
    fun getCurrentUserToken(): String?
    fun deleteCurrentUserToken()

}