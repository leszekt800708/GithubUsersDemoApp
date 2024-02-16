package com.lt.githubusersapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lt.githubusersapp.domain.User
import com.lt.githubusersapp.domain.UserDetails
import com.lt.githubusersapp.domain.UsersRepository
import com.lt.githubusersapp.remote.datasource.GithubUsersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubUsersRepository @Inject constructor(
    private val usersDataSource: UsersDataSource
) : UsersRepository {
    override suspend fun getUsers(perPage: Int): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = perPage, initialLoadSize = perPage),
            pagingSourceFactory = {
                GithubUsersPagingSource(usersDataSource)
            }
        ).flow
    }

    override suspend fun getUserDetails(login: String): UserDetails {
        return usersDataSource.getUser(login)
    }
}
