package com.mobilejazz.wildhike.features.home.data.datasource

import com.mobilejazz.wildhike.features.home.data.datasource.local.HomeLocalDataSource
import com.mobilejazz.wildhike.features.home.data.datasource.local.IHomeLocalDataSource
import com.mobilejazz.wildhike.features.home.data.datasource.remote.HomeRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideHomeRemoteDataSource(retrofit: Retrofit): HomeRemoteDataSource {
        return retrofit.create(HomeRemoteDataSource::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface HomeLocalDataSourceModule {

    @Binds
    @Singleton
    fun bindHomeLocalDataSource(HomeLocalDataSource: HomeLocalDataSource): IHomeLocalDataSource
}