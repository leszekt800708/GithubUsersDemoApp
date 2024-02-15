package com.lt.githubusersapp.domain

import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(private val repository: UsersRepository) {
    suspend fun getUserDetails(login: String): UserDetails {
        return repository.getUserDetails(login)
    }
}