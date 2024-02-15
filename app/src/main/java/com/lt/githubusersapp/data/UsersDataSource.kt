package com.lt.githubusersapp.data

import com.lt.githubusersapp.domain.User
import com.lt.githubusersapp.domain.UserDetails

interface UsersDataSource {
    suspend fun getUsers(query: String, perPage: Int, page: Int): List<User>
    suspend fun getUser(login: String): UserDetails
}