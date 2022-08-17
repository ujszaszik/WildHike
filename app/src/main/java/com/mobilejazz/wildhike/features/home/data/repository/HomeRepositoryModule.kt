package com.mobilejazz.wildhike.features.home.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeRepositoryModule {

    @Binds
    @Singleton
    fun bindSignInRepository(signInRepository: HomeRepository): IHomeRepository
}