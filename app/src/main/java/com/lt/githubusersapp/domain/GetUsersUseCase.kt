package com.lt.githubusersapp.domain

import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UsersRepository) {
    suspend fun getUsers(query: String, perPage: Int, page: Int): List<User> {
        return repository.getUsers(query, perPage, page)
    }
}