package com.lt.githubusersapp.data

import androidx.paging.*
import com.lt.githubusersapp.data.datastore.UserDetailsDataStore
import com.lt.githubusersapp.data.datastore.UsersPagedDataStore
import com.lt.githubusersapp.data.local.UserDatabase
import com.lt.githubusersapp.data.mappers.toUser
import com.lt.githubusersapp.data.mappers.toUserDetails
import com.lt.githubusersapp.domain.User
import com.lt.githubusersapp.domain.UserDetails
import com.lt.githubusersapp.domain.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class GithubUsersRepository @Inject constructor(
    usersPagedDataStore: UsersPagedDataStore,
    @Named("PAGE_SIZE") pageSize: Int,
    private val userDatabase: UserDatabase,
    private val userDetailsDataStore: UserDetailsDataStore
) : UsersRepository {
    @OptIn(ExperimentalPagingApi::class)
    private val pager: Pager<Int, UserEntity> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            initialLoadSize = pageSize
        ),
        remoteMediator = usersPagedDataStore,
        pagingSourceFactory = { userDatabase.dao.pagingSource() }
    )

    override suspend fun getUsers(): Flow<PagingData<User>> {
        return pager.flow.map { pagingData ->
            pagingData.map { it.toUser() }
        }
    }

    override suspend fun getUserDetails(login: String): UserDetails {
        return userDetailsDataStore.getUserDetails(login).toUserDetails()
    }
}
