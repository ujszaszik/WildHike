package com.mobilejazz.wildhike.features.signin.data.datasource

import com.mobilejazz.wildhike.features.signin.data.datasource.local.ISignInLocalDataSource
import com.mobilejazz.wildhike.features.signin.data.datasource.local.SignInLocalDataSource
import com.mobilejazz.wildhike.features.signin.data.datasource.remote.SignInRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignInRemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideSignInRemoteDataSource(retrofit: Retrofit): SignInRemoteDataSource {
        return retrofit.create(SignInRemoteDataSource::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface SignInLocalDataSourceModule {

    @Binds
    @Singleton
    fun bindSignInLocalDataSource(signInLocalDataSource: SignInLocalDataSource): ISignInLocalDataSource
}