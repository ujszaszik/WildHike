package com.mobilejazz.wildhike.navigation.arguments

import android.util.Base64
import androidx.navigation.NavBackStackEntry
import com.squareup.moshi.Moshi

inline fun <reified T> NavBackStackEntry.retainParam(param: String): T? {
    return try {
        arguments?.getString(param)?.substring(param.length + 2)?.convertFromJson()
    } catch (thr: Throwable) {
        null
    }
}

inline fun <reified T> NavBackStackEntry.retainEncodedParam(param: String): T? {
    return try {
        val encodedJson = arguments?.getString(param)?.substring(param.length + 2)
        return String(Base64.decode(encodedJson, Base64.DEFAULT)).convertFromJson()
    } catch (thr: Throwable) {
        null
    }
}

inline fun <reified T> String.convertFromJson(): T? {
    return Moshi.Builder().build().adapter(T::class.java).fromJson(this)
}