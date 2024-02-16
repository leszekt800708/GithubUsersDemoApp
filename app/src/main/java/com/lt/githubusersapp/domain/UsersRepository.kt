package com.lt.githubusersapp.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(perPage: Int): Flow<PagingData<User>>
    suspend fun getUserDetails(login: String): UserDetails
}