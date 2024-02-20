package com.lt.githubusersapp.remote.datasource

import com.lt.githubusersapp.data.UserDetailsEntity
import com.lt.githubusersapp.data.UserEntity
import com.lt.githubusersapp.data.UsersDataSource
import com.lt.githubusersapp.remote.api.UsersApi
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val usersApi: UsersApi,
) : UsersDataSource {
    override suspend fun getUsers(perPage: Int, startId: Int): List<UserEntity> {
        return usersApi.getUsers(perPage = perPage, startId = startId).map {
            UserEntity(
                id = it.userId,
                login = it.login,
                avatarUrl = it.avatarUrl
            )
        }
    }

    override suspend fun getUser(login: String): UserDetailsEntity {
        val user = usersApi.getUserDetails(login)
        return UserDetailsEntity(
            id = user.userId,
            login = user.login,
            avatarUrl = user.avatarUrl,
            bio = user.bio ?: "",
            publicRepos = user.publicRepos,
            followers = user.followers,
            following = user.following
        )
    }
}