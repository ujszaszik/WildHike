package com.mobilejazz.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

fun SharedPreferences.saveString(key: String, value: String?) {
    edit(commit = true) {
        putString(key, value)
    }
}