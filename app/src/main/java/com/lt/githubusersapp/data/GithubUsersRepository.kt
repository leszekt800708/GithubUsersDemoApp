package com.lt.githubusersapp.data

import com.lt.githubusersapp.domain.User
import com.lt.githubusersapp.domain.UserDetails
import com.lt.githubusersapp.domain.UsersRepository
import javax.inject.Inject

class GithubUsersRepository @Inject constructor(
    private val usersDataSource: UsersDataSource
) : UsersRepository {
    override suspend fun getUsers(query: String, perPage: Int, page: Int): List<User> {
        return usersDataSource.getUsers(query, perPage, page)
    }

    override suspend fun getUserDetails(login: String): UserDetails {
        return usersDataSource.getUser(login)
    }
}
