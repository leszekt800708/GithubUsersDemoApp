package com.lt.githubusersapp.di

import com.lt.githubusersapp.data.local.UserDatabase
import dagger.Module
import dagger.Provides

@Module
class RemoteMediatorModule {
    @Provides
    @ApplicationScope
    fun providesUsersRemoteMediator(
        db: UserDatabase,
        usersRemoteDataSource: com.lt.githubusersapp.data.UsersDataSource
    ): com.lt.githubusersapp.data.datastore.UsersPagedDataStore {
        return com.lt.githubusersapp.data.datastore.UsersPagedDataStore(
            db = db, usersRemoteDataSource = usersRemoteDataSource
        )
    }
}