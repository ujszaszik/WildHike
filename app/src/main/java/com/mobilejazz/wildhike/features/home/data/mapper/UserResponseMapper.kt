package com.mobilejazz.wildhike.features.home.data.mapper

import com.mobilejazz.network.mapper.BaseMapper
import com.mobilejazz.wildhike.features.home.data.datasource.remote.UserResponse
import com.mobilejazz.wildhike.features.home.data.model.UserInfo

class UserResponseMapper : BaseMapper<UserResponse, UserInfo> {

    override fun map(remote: UserResponse): UserInfo {
        return UserInfo("${remote.firstName} ${remote.lastName}")
    }
}