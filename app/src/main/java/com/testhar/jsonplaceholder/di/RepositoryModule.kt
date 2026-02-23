package com.testhar.jsonplaceholder.di

import com.testhar.jsonplaceholder.data.repository.UserRepositoryImpl
import com.testhar.jsonplaceholder.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl : UserRepositoryImpl
    ) : UserRepository
}