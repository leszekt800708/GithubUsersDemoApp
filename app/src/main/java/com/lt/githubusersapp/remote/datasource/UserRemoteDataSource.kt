package com.lt.githubusersapp.remote.datasource

import com.lt.githubusersapp.data.UsersDataSource
import com.lt.githubusersapp.domain.User
import com.lt.githubusersapp.domain.UserDetails
import com.lt.githubusersapp.remote.api.UsersApi
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val usersApi: UsersApi
) : UsersDataSource {
    override suspend fun getUsers(query: String, perPage: Int, page: Int): List<User> {
        return usersApi.getUsers(query, perPage, page).map { userApiEntity ->
            User(
                id = userApiEntity.userId,
                login = userApiEntity.login,
                avatarUrl = userApiEntity.avatarUrl
            )
        }
    }

    override suspend fun getUser(login: String): UserDetails {
        val user = usersApi.getUserDetails(login)
        return UserDetails(
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