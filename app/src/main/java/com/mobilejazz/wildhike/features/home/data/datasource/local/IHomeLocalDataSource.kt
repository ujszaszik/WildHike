package com.mobilejazz.wildhike.features.home.data.datasource.local

interface IHomeLocalDataSource {
    fun getCurrentUserToken(): String?
    fun deleteCurrentUserToken()
}