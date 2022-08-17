package com.mobilejazz.preferences

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EncryptedSharedPrefs

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrivateSharedPrefs