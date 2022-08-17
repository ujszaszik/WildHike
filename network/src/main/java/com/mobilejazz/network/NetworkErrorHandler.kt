package com.mobilejazz.network

import com.mobilejazz.network.call.Resource
import timber.log.Timber
import java.net.SocketTimeoutException

object NetworkErrorHandler {

    fun <T> handleThrowable(throwable: Throwable): Resource<T> {
        Timber.e(throwable)
        return Resource.error(message = getErrorMessageByThrowable(throwable))
    }

    private fun getErrorMessageByThrowable(throwable: Throwable): String {
        return if (!NetworkUtil.isConnected) ApiErrorCodes.NO_CONNECTION.message
        else if (throwable is SocketTimeoutException) ApiErrorCodes.TIMEOUT.message
        else ApiErrorCodes.DEFAULT.message
    }
}