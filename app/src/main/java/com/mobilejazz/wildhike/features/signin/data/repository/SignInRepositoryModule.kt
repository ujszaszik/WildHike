package com.mobilejazz.wildhike.features.signin.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SignInRepositoryModule {

    @Binds
    @Singleton
    fun bindSignInRepository(signInRepository: SignInRepository): ISignInRepository
}