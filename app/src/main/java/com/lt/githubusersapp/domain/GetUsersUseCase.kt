package com.lt.githubusersapp.domain

import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepository,
) {
    suspend fun getUsers(): Flow<PagingData<User>> {
        return withContext(Dispatchers.IO) {
            repository.getUsers()
        }
    }
}