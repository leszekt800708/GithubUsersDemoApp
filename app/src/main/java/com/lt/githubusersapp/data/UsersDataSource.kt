package com.lt.githubusersapp.data

interface UsersDataSource {
    suspend fun getUsers(perPage: Int, startId: Int): List<UserEntity>
    suspend fun getUser(login: String): UserDetailsEntity
}