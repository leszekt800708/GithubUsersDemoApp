package com.lt.githubusersapp.data

import com.lt.githubusersapp.domain.User
import com.lt.githubusersapp.domain.UserDetails

interface UsersDataSource {
    suspend fun getUsers(perPage: Int, startId: Int): List<User>
    suspend fun getUser(login: String): UserDetails
}