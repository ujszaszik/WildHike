package com.mobilejazz.wildhike.features.home.data.repository

import com.mobilejazz.network.operation.networkFlow
import com.mobilejazz.wildhike.coroutines.ResourceFlow
import com.mobilejazz.wildhike.features.home.data.datasource.local.IHomeLocalDataSource
import com.mobilejazz.wildhike.features.home.data.datasource.remote.HomeRemoteDataSource
import com.mobilejazz.wildhike.features.home.data.model.UserInfo
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val localDataSource: IHomeLocalDataSource,
    private val remoteDataSource: HomeRemoteDataSource
) : IHomeRepository {

    override fun getCurrentUserName(): ResourceFlow<UserInfo> {
        return networkFlow {
            remoteDataSource.getUser(getCurrentUserToken())
        }
    }

    override fun getCurrentUserToken(): String? {
        return localDataSource.getCurrentUserToken()
    }

    override fun deleteCurrentUserToken() = localDataSource.deleteCurrentUserToken()
}