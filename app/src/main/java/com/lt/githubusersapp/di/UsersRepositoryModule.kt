package com.lt.githubusersapp.di

import com.lt.githubusersapp.data.GithubUsersRepository
import com.lt.githubusersapp.domain.UsersRepository
import dagger.Binds
import dagger.Module

@Module
interface UsersRepositoryModule {
    @Binds
    @ApplicationScope
    fun bindsUsersRepository(githubUsersRepository: GithubUsersRepository): UsersRepository
}