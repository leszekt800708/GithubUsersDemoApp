package com.lt.githubusersapp.di

import com.lt.githubusersapp.data.UsersDataSource
import com.lt.githubusersapp.remote.datasource.UserRemoteDataSource
import dagger.Binds
import dagger.Module

@Module
interface UsersDataSourceModule {
    @Binds
    @ApplicationScope
    fun bindUsersDataSource(remoteDataSource: UserRemoteDataSource): UsersDataSource
}