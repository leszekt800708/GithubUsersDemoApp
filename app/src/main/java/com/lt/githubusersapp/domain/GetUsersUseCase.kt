package com.lt.githubusersapp.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UsersRepository) {
    suspend fun getUsers(perPage: Int): Flow<PagingData<User>> {
        return repository.getUsers(perPage)
    }
}