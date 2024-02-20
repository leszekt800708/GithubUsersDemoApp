package com.lt.githubusersapp.di

import com.lt.githubusersapp.data.datastore.UsersPagedDataStore
import com.lt.githubusersapp.data.local.UserDatabase
import com.lt.githubusersapp.remote.datasource.UserRemoteDataSource
import dagger.Module
import dagger.Provides


@Module
class UsersPagedDataStoreModule {
    @Provides
    @ApplicationScope
    fun provideUsersPagedDataStore(
        userDb: UserDatabase,
        usersRemoteDataSource: UserRemoteDataSource
    ): UsersPagedDataStore {
        return UsersPagedDataStore(
            db = userDb,
            usersRemoteDataSource = usersRemoteDataSource
        )
    }
}
