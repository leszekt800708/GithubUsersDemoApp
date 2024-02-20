package com.lt.githubusersapp.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: UsersRepository,
) {
    suspend fun getUserDetails(login: String): UserDetails {
        return withContext(Dispatchers.IO) { repository.getUserDetails(login) }
    }
}