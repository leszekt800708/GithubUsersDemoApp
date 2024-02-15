package com.lt.githubusersapp.domain

interface UsersRepository {
    suspend fun getUsers(query: String, perPage: Int, page: Int): List<User>
    suspend fun getUserDetails(login: String): UserDetails
}